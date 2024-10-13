package com.start.clubproject.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.start.clubproject.entities.Email;
import com.start.clubproject.entities.Login;
import com.start.clubproject.entities.Password;
import com.start.clubproject.entities.User;
import com.start.clubproject.entities.enums.UserRole;
import com.start.clubproject.repositories.EmailRepository;
import com.start.clubproject.repositories.LoginRepository;
import com.start.clubproject.repositories.PasswordRepository;
import com.start.clubproject.repositories.UserRepositoty;

import jakarta.transaction.Transactional;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepositoty userRepository;

	@Autowired
	private EmailRepository emailRepository;

	@Autowired
	private PasswordRepository passwordRepository;

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public void run(String... args) throws Exception {

		User c1 = new User("Roberto Arruda", UserRole.USER);
		User c2 = new User("Sandro Barabosa", UserRole.USER);
		User c3 = new User("Rony Pedrosa", UserRole.USER);
		User c4 = new User("Claudio Lima", UserRole.USER);
		userRepository.saveAll(Arrays.asList(c1, c2, c3, c4));

		Email email1 = new Email("roberto@gmail.com", c1, false);
		Email email2 = new Email("roberto@hotmail.com", c1, true);
		Email email3 = new Email("sandro@gmail.com", c2, true);
		Email email4 = new Email("claudio@gmail.com", c4, true);
		Email email5 = new Email("rony@gmail.com", c3, true);
		Email email6 = new Email("rony@uol.com", c3, false);
		emailRepository.saveAll(Arrays.asList(email1, email2, email3, email4, email5, email6));
		try {
			Password p1 = new Password(null, "root123", c1);
			Password p2 = new Password(null, "root1234", c2);
			Password p3 = new Password(null, "root12345", c3);
			Password p4 = new Password(null, "root432", c4);
			Password p5 = new Password(null, "root321", c4);
			p5.setIsValid(false);
			passwordRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		Login l1 = setLogin(c1);
		Login l2 = setLogin(c2);
		Login l3 = setLogin(c3);
		Login l4 = setLogin(c4);
		loginRepository.saveAll(Arrays.asList(l1,l2,l3,l4));
	}
	
	@Transactional
	private Login setLogin(User user) {
		System.out.println("USER ===>>>" + user);
		Email email = emailRepository.findByUserAndIsMainTrue(user);
		System.out.println("EMAIL ===>>> " + email);
		Password password = passwordRepository.findByUserAndIsValidTrue(user);
		System.out.println("PASSWORD ===>>> " + password);
		return new Login(user, email, password, user.getRole());
	}
}
