package com.start.clubproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.start.clubproject.entities.Email;
import com.start.clubproject.entities.Login;
import com.start.clubproject.entities.Password;
import com.start.clubproject.entities.User;
import com.start.clubproject.entities.dtos.AuthenticationDto;
import com.start.clubproject.entities.dtos.LoginResponseDto;
import com.start.clubproject.entities.dtos.RegisterDto;
import com.start.clubproject.services.EmailService;
import com.start.clubproject.services.LoginService;
import com.start.clubproject.services.PasswordService;
import com.start.clubproject.services.TokenService;
import com.start.clubproject.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private PasswordService passwordService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody AuthenticationDto data) {
		var userNamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
		var auth = authenticationManager.authenticate(userNamePassword);
		System.out.println("TestConfig -> login -> 'auth' => " + auth);
		
		var token = tokenService.generateToken((Login) auth.getPrincipal());
		System.out.println("TestConfig -> login -> 'token' => " + token);
		
		return ResponseEntity.ok(new LoginResponseDto(token));
	}

	@PostMapping("/register")
	public ResponseEntity<Void> register(@RequestBody RegisterDto data) {
		if(this.emailService.findByEmail(data.email()) != null)
			return ResponseEntity.badRequest().build();
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

		User createdUser = userService.create(data.name(), data.role());
		Email createdEmail = emailService.create(data.email(), createdUser, data.isMain());
		Password createdPassword = passwordService.create(createdUser, encryptedPassword);
		loginService.create(createdUser, createdEmail, createdPassword, createdUser.getRole());
		
		return ResponseEntity.ok().build();		
	}
}
