package com.medica.integration.service.diagnosis.domain;

import java.util.ArrayList;
import java.util.List;

public class DiagnosisForm {

	private List<FieldGroup> formFieldGroups = new ArrayList<FieldGroup>();

	public List<FieldGroup> getFormFieldGroups() {
		return formFieldGroups;
	}

	public void setFormFieldGroups(List<FieldGroup> formFieldGroups) {
		this.formFieldGroups = formFieldGroups;
	}
	
	public void addFormFieldGroup(FieldGroup group) {
		this.formFieldGroups.add(group);
	}
}
