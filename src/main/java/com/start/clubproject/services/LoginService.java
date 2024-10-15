package com.start.clubproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.start.clubproject.entities.Email;
import com.start.clubproject.entities.Login;
import com.start.clubproject.entities.Password;
import com.start.clubproject.entities.User;
import com.start.clubproject.entities.enums.UserRole;
import com.start.clubproject.repositories.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	public Login create(User user, Email email, Password password, UserRole role)  {
		return loginRepository.save(new Login(user, email, password, role));
	}
	
	public UserDetails findByEmail(String email) {
		UserDetails entity = loginRepository.findByEmail(email);
		System.out.println("Classe LoginService -> findByEmail => " + entity);
		return entity;
	}
}
