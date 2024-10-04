package com.start.clubproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.clubproject.entities.Email;
import com.start.clubproject.entities.User;
import com.start.clubproject.repositories.EmailRepository;
import com.start.clubproject.repositories.UserRepositoty;

@Service
public class UserService {

	@Autowired
	private UserRepositoty repository;
	
	@Autowired
	private EmailRepository emailRepository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		try {
			return repository.findById(id).get();
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public User findByEmail(String email) {
		Email entity = emailRepository.findByEmail(email);
		return entity.getClient();
	}
}
