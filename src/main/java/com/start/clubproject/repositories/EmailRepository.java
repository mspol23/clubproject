package com.start.clubproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.start.clubproject.entities.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {

	Email findByEmail(String e);
}
