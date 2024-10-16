package com.start.clubproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.start.clubproject.entities.Email;
import com.start.clubproject.entities.User;

public interface EmailRepository extends JpaRepository<Email, String> {
	Email findByEmail(String email);
	
	@Query("SELECT e.user FROM Email e WHERE e.email = :email")
	User findUserByEmail(@Param("email") String email);
	
	Email findByUserAndIsMainTrue(User user);	
}
