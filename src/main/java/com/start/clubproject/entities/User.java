package com.start.clubproject.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.start.clubproject.entities.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String name;
	private UserRole role;
	
	@OneToMany(mappedBy = "user")
	private List<Email> emails = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	private List<Password> passwords = new ArrayList<>();

	
	public User() {
	}

	public User(String name, UserRole role) {
		super();
		this.name = name;
		this.role = role;
	}
	
	public Email getMainEmail() {
		return emails.stream().filter(item -> item.getIsMain() == true).findAny().get();
	}

	public Password getValidPassword() {
		return passwords.stream().filter(item -> item.getIsValid() == true).findAny().get();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public List<Email> getEmails() {
		return emails;
	}
	
	public List<Password> getPasswords() {
		return passwords;
	}

	@Override
	public int hashCode() {
		return Objects.hash(emails, id, name, passwords, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(emails, other.emails) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(passwords, other.passwords) && role == other.role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
}
