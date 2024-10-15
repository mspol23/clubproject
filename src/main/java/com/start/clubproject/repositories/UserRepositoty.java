package com.start.clubproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.start.clubproject.entities.User;

public interface UserRepositoty extends JpaRepository<User, String> {
	
}
