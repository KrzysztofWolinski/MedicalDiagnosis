package com.medica.integration.service.diagnosis.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medica.core.controller.DiagnosisCoreService;
import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.DiagnosisCoreRule;
import com.medica.core.domain.communication.analyse.AnalyseRequest;
import com.medica.core.domain.communication.analyse.AnalyseResponse;
import com.medica.core.domain.communication.analyse.AnalyseResponseStatus;
import com.medica.core.domain.communication.perform.PerformRequest;
import com.medica.core.domain.communication.perform.PerformResponse;
import com.medica.integration.controller.diagnosis.domain.FormSubmitStatus;
import com.medica.integration.domain.diagnosis.DataPiece;
import com.medica.integration.domain.diagnosis.DiagnosisData;
import com.medica.integration.domain.diagnosis.DiagnosisResult;
import com.medica.integration.domain.diagnosis.DiagnosisRule;
import com.medica.integration.domain.user.User;
import com.medica.integration.repository.DiagnosisDataRepository;
import com.medica.integration.repository.DiagnosisResultRepository;
import com.medica.integration.repository.DiagnosisRuleRepository;
import com.medica.integration.repository.UserRepository;
import com.medica.integration.service.converters.DiagnosisDataConverter;
import com.medica.integration.service.converters.DiagnosisResultConverter;
import com.medica.integration.service.converters.DiagnosisRuleConverter;
import com.medica.integration.service.diagnosis.DiagnosisService;
import com.medica.integration.service.diagnosis.domain.DiagnosisForm;
import com.medica.integration.service.diagnosis.domain.FieldGroup;
import com.medica.integration.service.diagnosis.domain.FieldValue;

public class DiagnosisServiceImpl implements DiagnosisService {

	@Inject
	private ObjectMapper objectMapper;
	
	@Inject
	private DiagnosisDataRepository diagnosisDataRepository;
	
	@Inject
	private DiagnosisRuleRepository diagnosisRuleRepository;
	
	@Inject
	private DiagnosisResultRepository diagnosisResultRepository;
	
	@Inject
	private UserRepository userRepository;
	
	@Inject
	private DiagnosisCoreService diagnosisCoreService; 
	
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
	public FormSubmitStatus acceptSubmittedForm(DiagnosisForm form, String username) {
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
			
			return FormSubmitStatus.OK;
			
		} else {
			return FormSubmitStatus.ERROR;
		}
		
	}
	
	private boolean validateForm(DiagnosisForm form) {
		// TODO implement 
		return true;
	}

	@Override
	public AnalyseResponseStatus requestDataAnalyse(User patient) {
		List<DiagnosisData> inputData = diagnosisDataRepository.getByPatient(patient);
		List<DiagnosisCoreData> convertedInputData = DiagnosisDataConverter.convertToDto(inputData);
		
		AnalyseRequest request = new AnalyseRequest();
		request.setData(convertedInputData);
		
		AnalyseResponse response = diagnosisCoreService.analyse(request);
		
		return response.getStatus();
	}

	@Override
	public void requestPerformDiagnosis(User patient) {
		DiagnosisData lastDataEntry = diagnosisDataRepository.findTop1ByPatientOrderByDateSubtmittedDesc(patient);
		List<DiagnosisRule> rules = diagnosisRuleRepository.findAll();
		
		DiagnosisCoreData convertedData = DiagnosisDataConverter.convertToDto(lastDataEntry);
		List<DiagnosisCoreRule> convertedRules = DiagnosisRuleConverter.convertToDto(rules);
		
		PerformRequest request = new PerformRequest();
		request.setLastData(convertedData);
		request.setRules(convertedRules);

		PerformResponse response = diagnosisCoreService.perform(request);
		
		if (response.getResult() != null) {
			DiagnosisResult result = DiagnosisResultConverter.convertToDao(response.getResult());
			
			result.setDiagnosedData(lastDataEntry);
			result.setPatient(patient);
			
			diagnosisResultRepository.saveAndFlush(result);
			
			// TODO inform patient?
		}
	}

}
