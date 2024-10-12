package com.start.clubproject.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import com.start.clubproject.entities.Email;
import com.start.clubproject.entities.User;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Embeddable
public class LoginPk implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToOne
	@MapsId
	private User user;
	
	@OneToOne
	@MapsId
	private Email email;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginPk other = (LoginPk) obj;
		return Objects.equals(email, other.email) && Objects.equals(user, other.user);
	}
}
