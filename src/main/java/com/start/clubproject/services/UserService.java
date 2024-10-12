package com.start.clubproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.clubproject.entities.User;
import com.start.clubproject.repositories.UserRepositoty;
import com.start.clubproject.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepositoty repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User create(User user) {
		User entity = new User(user.getName(), user.getRole());
		repository.save(entity);
		return entity;
	}

	public User update(User user, Long id) {
		User entity = repository.findById(id).get();
		entity.setName(user.getName());
		repository.save(entity);
		return entity;
	}

	public User delete(Long id) {
		User user = findById(id);
		repository.deleteById(id);
		return user;
	}
}
