package app.car.cap07.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration()
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.
                authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }



    @Override
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {


        /*
        String password = "{noop}password";

        User.UserBuilder driver = User.builder()
                .username("driver")
                .password(password)
                .roles("DRIVER");

        User.UserBuilder passenger = User.builder()
                .username("passenger")
                .password(password)
                .roles("PASSENGER");

        User.UserBuilder admin = User.builder()
                .username("admin")
                .password(password)
                .roles("ADMIN");


        auth.inMemoryAuthentication()
                .withUser(driver)
                .withUser(passenger)
                .withUser(admin)
        ;
        */

        String queryUsers = "select username, password, enabled from user where username=?";
        String queryRoles = "select u.username, r.roles from user_roles r, user u where r.user_id = u.id and u.username=?";

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(queryUsers)
                .authoritiesByUsernameQuery(queryRoles);


    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
