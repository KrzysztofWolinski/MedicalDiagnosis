package com.medica.integration.controller.auth.domain;

public enum AuthenticationStatus {

	OK("OK"),
	EXPIRED("EXPIRED"),
	INVALID("INVALID");
	
	private final String status;
	
	AuthenticationStatus(String status) {
		this.status = status;
	}
	
	String getStatus() {
		return this.status;
	}
}
