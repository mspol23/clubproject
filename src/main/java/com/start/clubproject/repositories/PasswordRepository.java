package com.start.clubproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.start.clubproject.entities.Password;

public interface PasswordRepository extends JpaRepository<Password, Long> {

}
