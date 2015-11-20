package com.medica.integration.controller.diagnosis.domain;


public class DiagnosisSubmitFormResponse {

	private FormSubmitStatus status;
	
	private String message;

	public DiagnosisSubmitFormResponse() {
		status = FormSubmitStatus.OK;
		message = "";
	}
	
	public DiagnosisSubmitFormResponse(FormSubmitStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public FormSubmitStatus getStatus() {
		return status;
	}

	public void setStatus(FormSubmitStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
