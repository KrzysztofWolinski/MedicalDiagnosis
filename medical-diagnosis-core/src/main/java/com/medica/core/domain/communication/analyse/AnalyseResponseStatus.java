package com.medica.core.domain.communication.analyse;

public enum AnalyseResponseStatus {

	OK("OK"),
	WARNING("WARNING");
	
	private final String status;
	
	AnalyseResponseStatus(String status) {
		this.status = status;
	}
	
	String getStatus() {
		return this.status;
	}
}
