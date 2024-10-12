package com.start.clubproject.entities;

import java.io.Serializable;

import com.start.clubproject.entities.pk.LoginPk;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "logins")
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LoginPk id;
	
	private String email;
	private String password;
		
	public Login() {
	}

	public Login(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
