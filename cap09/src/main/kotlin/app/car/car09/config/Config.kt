package app.car.car09.config

import app.car.car09.domain.User
import app.car.car09.domain.UserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import javax.annotation.PostConstruct
import javax.sql.DataSource

@Configuration
@EnableAutoConfiguration
class AppConfig {

    @Bean
    fun messageSource(): MessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasename("classpath:i18n/messages")
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }
}

@Configuration
class LoadUserConfig(
        val passwordEncoder: PasswordEncoder,
        val userRepository: UserRepository
) {

    @PostConstruct
    fun init() {
        val admin = User(
                password = passwordEncoder.encode("password"),
                roles = listOf("ROLE_ADMIN"),
                username = "admin",
                enabled = true
        )
        userRepository.save(admin)
    }
}


@Configuration
class OpenAPIConfig {
    @Bean
    fun openAPIDocumentation(): OpenAPI {
        return OpenAPI()
                .info(
                        Info()
                                .title("C.A.R. API")
                                .description("API do sistema C.A.R., de facilitação de mobilidade urbana")
                                .version("v1.0")
                                .contact(Contact()
                                        .name("Alexandre Saudate")
                                        .email("alesaudate@gmail.com")
                                )
                )
    }
}


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
class SecurityConfig(
        val dataSource: DataSource
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        http.cors()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http
                .authorizeRequests()
                .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
    }

    public override fun configure(auth: AuthenticationManagerBuilder) {
        val queryUsers = "select username, password, enabled from user where username=?"
        val queryRoles = "select u.username, r.roles from user_roles r, user u where r.user_id = u.id and u.username=?"
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(queryUsers)
                .authoritiesByUsernameQuery(queryRoles)
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("https://bestcars.com")
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "PATCH")
        configuration.addAllowedHeader("*")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}

@Configuration
class JacksonConfiguration {

    @Bean
    fun mappingJackson2HttpMessageConverter(objectMapper: ObjectMapper): MappingJackson2HttpMessageConverter {
        return MappingJackson2HttpMessageConverter().apply {
            this.objectMapper = objectMapper.apply {
                registerModule(KotlinModule())
            }
        }
    }
}