package com.medica.integration.service.diagnosis.domain;

import java.util.ArrayList;
import java.util.List;

public class FieldGroup {

	private String name;
	
	private List<FieldValue> fieldList = new ArrayList<FieldValue>();

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
	
	public void addField(FieldValue field) {
		this.fieldList.add(field);
	}
	
}
