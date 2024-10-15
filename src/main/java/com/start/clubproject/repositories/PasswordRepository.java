package com.start.clubproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.start.clubproject.entities.Password;
import com.start.clubproject.entities.User;

public interface PasswordRepository extends JpaRepository<Password, String> {
	Password findByUserAndIsValidTrue(User user);
}
