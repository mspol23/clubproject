package com.start.clubproject.controllers.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.start.clubproject.entities.exceptions.StandardErrorMessage;
import com.start.clubproject.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExcetionMessageHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardErrorMessage> ResourceNotFound(ResourceNotFoundException e, HttpServletRequest req) {
		String error = "Resource Not Found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardErrorMessage errorObject = new StandardErrorMessage(Instant.now(), status.value(), error, e.getMessage(), req.getRequestURI());
		return ResponseEntity.status(status).body(errorObject);
	}
	
}
