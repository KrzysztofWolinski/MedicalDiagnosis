package com.medica.integration.controller.auth.domain;

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
