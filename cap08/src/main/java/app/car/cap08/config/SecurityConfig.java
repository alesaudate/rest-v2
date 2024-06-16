package app.car.cap08.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class SecurityConfig {


    @Autowired
    DataSource dataSource;

    @Bean
    public DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(CsrfConfigurer::disable);
        http.sessionManagement(configure -> configure.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(configure ->
                configure
                        .requestMatchers(HttpMethod.GET, "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**")
                        .permitAll());
        http.authorizeHttpRequests(configure -> configure.anyRequest().authenticated());
        http.httpBasic(withDefaults());
        http.headers(c1 -> c1.frameOptions(c2 -> c2.disable()));
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("select username, password, enabled from users where username=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select u.username, r.roles from user_roles r, users u where r.user_id = u.id and u.username=?");
        return jdbcUserDetailsManager;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
