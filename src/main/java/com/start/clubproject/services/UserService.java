package com.start.clubproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.clubproject.entities.User;
import com.start.clubproject.entities.enums.UserRole;
import com.start.clubproject.repositories.UserRepositoty;
import com.start.clubproject.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepositoty repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User create(String name, UserRole role) {
		return repository.save(new User(name, role));
	}

	public User update(User user, String id) {
		User entity = repository.findById(id).get();
		entity.setName(user.getName());
		repository.save(entity);
		return entity;
	}

	public User delete(String id) {
		User user = findById(id);
		repository.deleteById(id);
		return user;
	}
}
