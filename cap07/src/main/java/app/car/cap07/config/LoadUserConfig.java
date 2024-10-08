package app.car.cap07.config;

import app.car.cap07.domain.User;
import app.car.cap07.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Configuration
public class LoadUserConfig {


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void init() {
        User admin = new User();
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setRoles(List.of("ROLE_ADMIN"));
        admin.setUsername("admin");
        admin.setEnabled(true);

        userRepository.save(admin);
    }


}
