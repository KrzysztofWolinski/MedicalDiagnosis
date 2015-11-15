package com.medica.integration.controller.diagnosis.domain.form;

public class SimpleFieldValue extends FieldValue {

	private FieldValueType type;
	
	private Object value;
	
	public FieldValueType getType() {
		return type;
	}

	public void setType(FieldValueType type) {
		this.type = type;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return this.value;
	}

}
