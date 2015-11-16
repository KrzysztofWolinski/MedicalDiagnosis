package com.medica.integration.service.diagnosis.domain;

import java.util.ArrayList;
import java.util.List;

public class DiagnosisForm {

	private List<FieldGroup> formFields = new ArrayList<FieldGroup>();

	public List<FieldGroup> getFormFields() {
		return formFields;
	}

	public void setFormFields(List<FieldGroup> formFields) {
		this.formFields = formFields;
	}
	
	public void addFormField(FieldGroup group) {
		this.formFields.add(group);
	}
}
