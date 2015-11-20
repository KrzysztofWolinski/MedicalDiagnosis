package com.medica.integration.service.diagnosis.impl;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medica.integration.domain.diagnosis.DataPiece;
import com.medica.integration.domain.diagnosis.DiagnosisData;
import com.medica.integration.domain.user.User;
import com.medica.integration.repository.DiagnosisDataRepository;
import com.medica.integration.repository.UserRepository;
import com.medica.integration.service.diagnosis.DiagnosisService;
import com.medica.integration.service.diagnosis.domain.DiagnosisForm;
import com.medica.integration.service.diagnosis.domain.FieldGroup;
import com.medica.integration.service.diagnosis.domain.FieldValue;

public class DiagnosisServiceImpl implements DiagnosisService {

	@Inject
	ObjectMapper objectMapper;
	
	@Inject
	DiagnosisDataRepository diagnosisDataRepository;
	
	@Inject
	UserRepository userRepository;
	
	@Value("${diagnosis.form.template_path}")
	private String formTemplatePath;
	
	@Override
	public DiagnosisForm getForm() {
		DiagnosisForm form = null;
		
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(this.formTemplatePath);
			form = objectMapper.readValue(inputStream, DiagnosisForm.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return form;
	}

	@Override
	public void acceptSubmittedForm(DiagnosisForm form, String username) {
		if (validateForm(form)) {
			DiagnosisData newDataSet = new DiagnosisData();
			
			for (FieldGroup group : form.getFormFieldGroups()) {
				for (FieldValue field : group.getFieldList()) {
					DataPiece dataPiece = new DataPiece();
					
					dataPiece.setName(field.getName());
					dataPiece.setType(field.getType());
					
					if (field.getValue() != null) {
						dataPiece.setValue(field.getValue().toString());
					}
					
					newDataSet.addDataPiece(dataPiece);
				}
			}
			
			User patient = this.userRepository.findByUsername(username);
			newDataSet.setPatient(patient);
			
			this.diagnosisDataRepository.saveAndFlush(newDataSet);
			
		} else {
			// TODO decide what to do if data is corrupted
		}
		
	}
	
	private boolean validateForm(DiagnosisForm form) {
		// TODO implement 
		return true;
	}

}
