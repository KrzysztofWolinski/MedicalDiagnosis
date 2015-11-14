package com.medica.integration.service.auth.domain;

import com.medica.integration.controller.auth.domain.AuthenticationStatus;

public class AuthenticationCheckResult {
	
	private String newToken;
	
	private AuthenticationStatus status;

	public String getNewToken() {
		return newToken;
	}

	public void setNewToken(String newToken) {
		this.newToken = newToken;
	}

	public AuthenticationStatus getStatus() {
		return status;
	}

	public void setStatus(AuthenticationStatus status) {
		this.status = status;
	}
	
	public boolean isStatus(AuthenticationStatus status) {
		return this.status.equals(status);
	}
	
	public AuthenticationCheckResult withStatusOk() {
		this.newToken = "";
		this.status = AuthenticationStatus.OK; 
		return this;
	}
	
	public AuthenticationCheckResult withStatusExpired(String newToken) {
		this.newToken = newToken;
		this.status = AuthenticationStatus.EXPIRED; 
		return this;
	}
	
	public AuthenticationCheckResult withStatusInvalid() {
		this.newToken = "";
		this.status = AuthenticationStatus.INVALID; 
		return this;
	}
}
