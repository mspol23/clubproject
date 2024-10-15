package com.start.clubproject.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "emails")
public class Email implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	private Boolean isValid;
	
	private Boolean isMain;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Email() {}

	public Email(String email, User user, Boolean isMain) {
		super();
		this.email = email;
		this.isValid = true;
		this.user = user;
		this.isMain = isMain;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsValid() {
		return isValid;		
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public String getId() {
		return id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Boolean getIsMain() {
		return isMain;
	}

	public void setIsMain(Boolean isMain) {
		this.isMain = isMain;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email, id, isMain, isValid, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(isMain, other.isMain) && Objects.equals(isValid, other.isValid)
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Email [id=" + id + ", email=" + email + ", isValid=" + isValid + "]";
	}	
}
