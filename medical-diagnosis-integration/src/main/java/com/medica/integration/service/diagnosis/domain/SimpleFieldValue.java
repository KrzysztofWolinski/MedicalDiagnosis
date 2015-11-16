package com.medica.integration.service.diagnosis.domain;

public class SimpleFieldValue extends FieldValue {

	private Object value;
	
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return this.value;
	}

}
