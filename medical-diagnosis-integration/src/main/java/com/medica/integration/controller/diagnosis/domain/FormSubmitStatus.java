package com.medica.integration.controller.diagnosis.domain;

public enum FormSubmitStatus {

	OK("OK"),
	ERROR("ERROR");
	
	private final String status;
	
	FormSubmitStatus(String status) {
		this.status = status;
	}
	
	String getStatus() {
		return this.status;
	}
	
}
