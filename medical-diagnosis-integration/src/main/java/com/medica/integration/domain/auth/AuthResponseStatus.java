package com.medica.integration.domain.auth;

public enum AuthResponseStatus {

	OK("OK"),
	ERROR("ERROR");
	
	private final String status;
	
	AuthResponseStatus(String status) {
		this.status = status;
	}
	
	String getStatus() {
		return this.status;
	}
}
