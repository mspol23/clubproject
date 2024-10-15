package com.start.clubproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.clubproject.entities.Email;
import com.start.clubproject.entities.User;
import com.start.clubproject.repositories.EmailRepository;
import com.start.clubproject.services.exceptions.ResourceNotFoundException;

@Service
public class EmailService {
		
	@Autowired
	public EmailRepository repository;
	
	public List<Email> findAll(){
		return repository.findAll();
	}
	
	public Email findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Email findByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	public User findUserByEmail(String email) {
	    return repository.findUserByEmail(email);
	}
	
	public Email create(String email, User user, Boolean isMain) {
		return repository.save(new Email(email, user, isMain));
	}
	
	public Email delete(String id) {
		Email email = findById(id);
		repository.deleteById(id);
		return email;
	}
	
	public Email findActiveEmailByUser(User user) {
		Email entity = repository.findByUserAndIsMainTrue(user);
		System.out.println("GET EMAIL BY USER findByUser(User user) ===>>>" + entity);
		return entity;
	}
}
