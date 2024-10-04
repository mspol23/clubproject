package com.start.clubproject.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.start.clubproject.entities.User;
import com.start.clubproject.entities.Email;
import com.start.clubproject.repositories.UserRepositoty;
import com.start.clubproject.repositories.EmailRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepositoty userService;

	@Autowired
	private EmailRepository emailRepository;

	@Override
	public void run(String... args) throws Exception {

		User c1 = new User("Roberto Arruda");
		User c2 = new User("Sandro Barabosa");
		User c3 = new User("Rony Pedrosa");
		User c4 = new User("Claudio Lima");

		Email email1 = new Email("roberto@gmail.com", Instant.now(), true, null);
		Email email2 = new Email("roberto@hotmail.com", Instant.now(), false, c1);
		Email email3 = new Email("sandro@gmail.com", Instant.now(), true, c2);
		Email email4 = new Email("claudio@gmail.com", Instant.now(), true, c4);
		Email email5 = new Email("rony@gmail.com", Instant.now(), true, c3);
		Email email6 = new Email("rony@uol.com", Instant.now(), false, c3);

		userService.saveAll(Arrays.asList(c1, c2, c3, c4));
		emailRepository.saveAll(Arrays.asList(email1, email2, email3, email4, email5, email6));

	}

}
