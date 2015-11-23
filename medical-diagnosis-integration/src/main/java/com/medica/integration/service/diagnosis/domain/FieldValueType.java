package com.medica.integration.service.diagnosis.domain;

public enum FieldValueType {

	STRING("String"),
	INTEGER("Integer"),
	FLOAT("Float"),
	DATE("Date");
	
	private final String type;
	
	FieldValueType(String type) {
		this.type = type;
	}
	
	String getType() {
		return this.type;
	}
	
}
