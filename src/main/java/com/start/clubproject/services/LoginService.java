package com.start.clubproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.clubproject.entities.Login;
import com.start.clubproject.repositories.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	public Login create(Login login) {
		Login entity = new Login();
		return entity;
	}
	
}
