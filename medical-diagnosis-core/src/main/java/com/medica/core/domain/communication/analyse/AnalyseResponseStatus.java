package com.medica.core.domain.communication.analyse;

public enum AnalyseResponseStatus {

	OK("OK"),
	INVALID("WARNING");
	
	private final String status;
	
	AnalyseResponseStatus(String status) {
		this.status = status;
	}
	
	String getStatus() {
		return this.status;
	}
}
