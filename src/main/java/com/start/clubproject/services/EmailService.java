package com.start.clubproject.services;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.clubproject.entities.Email;
import com.start.clubproject.entities.User;
import com.start.clubproject.repositories.EmailRepository;
import com.start.clubproject.services.exceptions.ResourceNotFoundException;

@Service
public class EmailService {
	
	public static DateTimeFormatter fmt = DateTimeFormatter.ISO_INSTANT;
	
	@Autowired
	public EmailRepository repository;
	
	public List<Email> findAll(){
		return repository.findAll();
	}
	
	public Email findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Email findByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	public User findUserByEmail(String email) {
	    return repository.findUserByEmail(email);
	}
	
	public Email create(Email email) {
		Email newEmail = new Email(email.getEmail(), email.getUser(), email.getIsMain());
		return repository.save(newEmail);
	}
	
	public Email delete(Long id) {
		Email email = findById(id);
		repository.deleteById(id);
		return email;
	}
}
