package com.medica.integration.service.diagnosis.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class FieldValue {

	private FieldValueType type;
	
	public abstract Object getValue();
	
	public FieldValueType getType() {
		return type;
	}

	public void setType(FieldValueType type) {
		this.type = type;
	}
}
