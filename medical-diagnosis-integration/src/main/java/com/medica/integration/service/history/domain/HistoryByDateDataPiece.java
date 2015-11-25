package com.medica.integration.service.history.domain;

import com.medica.integration.service.diagnosis.domain.FieldValueType;

public class HistoryByDateDataPiece {

	private String name;
	
	private String value;
	
	private FieldValueType type;
	
	public HistoryByDateDataPiece() {
		
	}
	
	public HistoryByDateDataPiece(String name, String value, FieldValueType type) {
		this.name = name;
		this.value = value;
		this.type = type;	
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public FieldValueType getType() {
		return type;
	}

	public void setType(FieldValueType type) {
		this.type = type;
	}
	
}
