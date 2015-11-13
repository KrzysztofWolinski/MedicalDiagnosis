package com.medica.integration.domain.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "credentials")
public class Credentials {

	@Id	
	private String username;
	
	private String password;
	
	private String token; 

	private Date tokenExpirationTime;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenExpirationTime() {
		return tokenExpirationTime;
	}

	public void setTokenExpirationTime(Date tokenExpirationTime) {
		this.tokenExpirationTime = tokenExpirationTime;
	}
}
