package app.car.cap09.config;

import app.car.cap09.domain.User;
import app.car.cap09.domain.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

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
