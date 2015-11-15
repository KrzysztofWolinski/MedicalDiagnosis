package com.medica.integration.controller.diagnosis.domain.form;

import java.util.List;

public class FieldGroup {

	private String name;
	
	private List<FieldValue> fieldList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FieldValue> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<FieldValue> fieldList) {
		this.fieldList = fieldList;
	}
	
}
