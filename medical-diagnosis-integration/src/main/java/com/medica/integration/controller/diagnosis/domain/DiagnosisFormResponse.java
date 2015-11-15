package com.medica.integration.controller.diagnosis.domain;

import java.util.ArrayList;
import java.util.List;

import com.medica.integration.controller.diagnosis.domain.form.FieldGroup;

public class DiagnosisFormResponse {

	private List<FieldGroup> fieldGroupList = new ArrayList<FieldGroup>();
	
	public List<FieldGroup> getFieldGroupList() {
		return this.fieldGroupList;
	}
	
	public void addFieldGroup(FieldGroup fieldGroup) {
		this.fieldGroupList.add(fieldGroup);
	}
}
