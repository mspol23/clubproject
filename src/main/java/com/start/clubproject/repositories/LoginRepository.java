package com.start.clubproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.start.clubproject.entities.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {
	UserDetails findByEmail(String email);
}
