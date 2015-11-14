package com.medica.integration.controller.auth.domain;


public class AuthResponseDto {

	private AuthenticationStatus status;
	
	private String token;
	
	public AuthenticationStatus getStatus() {
		return status;
	}

	public void setStatus(AuthenticationStatus status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
