package com.medica.integration.domain.auth;

public class AuthResponseDto {

	private AuthResponseStatus status;
	
	private String token;
	
	public AuthResponseStatus getStatus() {
		return status;
	}

	public void setStatus(AuthResponseStatus status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
