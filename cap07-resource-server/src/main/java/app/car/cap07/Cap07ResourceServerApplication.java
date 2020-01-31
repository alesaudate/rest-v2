package app.car.cap07;

import app.car.cap07.domain.User;
import app.car.cap07.domain.UserRepository;
import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Cap07ResourceServerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Cap07ResourceServerApplication.class, args);

		PasswordEncoder encoder = ctx.getBean(PasswordEncoder.class);
		UserRepository repository = ctx.getBean(UserRepository.class);

		User admin = new User();
		admin.setPassword(encoder.encode("password"));
		admin.setRoles(Arrays.asList("ROLE_ADMIN"));
		admin.setUsername("admin");
		admin.setEnabled(true);

		repository.save(admin);
	}

}
