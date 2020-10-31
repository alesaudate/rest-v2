package app.car.cap08.config;

import app.car.cap08.domain.User;
import app.car.cap08.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Configuration
public class LoadUserConfig {


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void init() {
        app.car.cap08.domain.User admin = new User();
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setRoles(Arrays.asList("ROLE_ADMIN"));
        admin.setUsername("admin");
        admin.setEnabled(true);

        userRepository.save(admin);
    }


}
