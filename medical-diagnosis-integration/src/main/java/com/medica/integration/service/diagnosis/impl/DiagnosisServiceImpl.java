package com.medica.integration.service.diagnosis.impl;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medica.integration.service.diagnosis.DiagnosisService;
import com.medica.integration.service.diagnosis.domain.DiagnosisForm;

public class DiagnosisServiceImpl implements DiagnosisService {

	@Inject
	ObjectMapper objectMapper;
	
	@Value("${diagnosis.form.template_path}")
	private String formTemplatePath;
	
	@Override
	public DiagnosisForm getForm() {
		DiagnosisForm form = null;
		
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(this.formTemplatePath);
			form = objectMapper.readValue(inputStream, DiagnosisForm.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return form;
	}

	@Override
	public void acceptSubmittedForm(DiagnosisForm form) {
		// TODO Auto-generated method stub
		
	}

}
