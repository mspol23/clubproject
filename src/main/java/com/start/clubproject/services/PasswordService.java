package com.start.clubproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.clubproject.entities.Password;
import com.start.clubproject.entities.User;
import com.start.clubproject.repositories.PasswordRepository;

@Service
public class PasswordService {
	
	@Autowired
	private PasswordRepository repository;
	
	public Password findByUser(User user) {
		Password entity = repository.findByUserAndIsValidTrue(user);
		System.out.println("FOUND PASSWORD ENTITY findByUser(User user) =====>>>" + entity);
		return entity;
	}
	
	public Password create(User user, String password ) {
		return repository.save(new Password(user, password));
	}
}
