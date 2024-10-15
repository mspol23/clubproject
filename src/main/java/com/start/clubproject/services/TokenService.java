package com.start.clubproject.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.start.clubproject.entities.Login;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(Login login) {
		System.out.println("JWT Secret: " + secret); // Adicione esta linha
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create()
					.withIssuer("my-api-login")
					.withSubject(login.getEmail())
					.withExpiresAt(genExpirationDate())
					.sign(algorithm);
			return token;
		} catch (JWTCreationException e) {
			throw new  RuntimeException("Error while generationg token.");
		}
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("my-api-login")
					.build()
					.verify(token)
					.getSubject();
//			var decodedJwt = verifier.verify(token);
//			String subject = decodedJwt.getSubject();
//			System.out.println("Token subject (email): " + subject);
//			return subject;
		} catch (JWTVerificationException e) {
			 System.out.println("JWT Verification failed: " + e.getMessage());
		     return "";
		}
	}
	
	public Instant genExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
