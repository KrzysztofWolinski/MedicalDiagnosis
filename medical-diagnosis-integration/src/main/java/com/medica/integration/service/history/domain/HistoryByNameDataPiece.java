package com.medica.integration.service.history.domain;

import java.util.Date;

import com.medica.integration.service.diagnosis.domain.FieldValueType;

public class HistoryByNameDataPiece {
	
	private Date date;
	
	private String value;
	
	private FieldValueType type;

	public HistoryByNameDataPiece() {
		
	}
	
	public HistoryByNameDataPiece(Date date, String value, FieldValueType type) {
		this.date = date;
		this.value = value;
		this.type = type;	
		
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
