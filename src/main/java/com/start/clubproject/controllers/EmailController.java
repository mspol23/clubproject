package com.start.clubproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.start.clubproject.entities.Email;
import com.start.clubproject.entities.User;
import com.start.clubproject.services.EmailService;

@RestController
@RequestMapping("/emails")
public class EmailController {

	@Autowired
	private EmailService service;
	
	@GetMapping
	public ResponseEntity<List<Email>> findAll(){
		List<Email> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Email> findByEmail(@RequestBody Email email){
		Email e = service.findByEmail(email.getEmail());
		return ResponseEntity.ok().body(e);
	}
	
	@PostMapping("/user")
	public ResponseEntity<User> findUserByEmail(@RequestBody Email	email){
		User entity = service.findUserByEmail(email.getEmail());
		return ResponseEntity.ok().body(entity);
	}
	
//	@PostMapping("/create")
//	public ResponseEntity<Email> create(@RequestBody Email email){
//		Email entity = service.create(email);
//		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUri();
//		return ResponseEntity.created(uri).body(entity);
//	}
	
}
