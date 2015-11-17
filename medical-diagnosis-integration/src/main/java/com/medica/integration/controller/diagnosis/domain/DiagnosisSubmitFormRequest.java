package com.medica.integration.controller.diagnosis.domain;

import com.medica.integration.service.diagnosis.domain.DiagnosisForm;

public class DiagnosisSubmitFormRequest {

	String username; 
	
	String token;
	
	DiagnosisForm data;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public DiagnosisForm getData() {
		return data;
	}

	public void setData(DiagnosisForm data) {
		this.data = data;
	}
}
