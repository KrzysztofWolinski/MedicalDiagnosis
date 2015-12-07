package com.medica.integration.controller.diagnosis;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.medica.core.domain.communication.analyse.AnalyseResponseStatus;
import com.medica.integration.controller.diagnosis.domain.DiagnosisFormRequestDto;
import com.medica.integration.controller.diagnosis.domain.DiagnosisSubmitFormRequest;
import com.medica.integration.controller.diagnosis.domain.DiagnosisSubmitFormResponse;
import com.medica.integration.controller.diagnosis.domain.FormSubmitStatus;
import com.medica.integration.domain.user.User;
import com.medica.integration.repository.UserRepository;
import com.medica.integration.service.auth.AuthService;
import com.medica.integration.service.auth.exceptions.InvalidCredentialsException;
import com.medica.integration.service.diagnosis.DiagnosisService;
import com.medica.integration.service.diagnosis.domain.DiagnosisForm;

@RestController
@RequestMapping("/diagnosis")
public class DiagnosisController {

	@Inject
	DiagnosisService diagnosisService;
	
	@Inject
	UserRepository userRepository;
	
	@Inject
	AuthService authService;
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	@ResponseBody
    public DiagnosisForm getForm(@RequestBody DiagnosisFormRequestDto request) throws InvalidCredentialsException {  	
		if (authService.isAuthorized(request.getUsername(), request.getToken())) {
			return diagnosisService.getForm();
		} else {
			throw new InvalidCredentialsException();
		}
	}
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	@ResponseBody
	public DiagnosisSubmitFormResponse submitData(@RequestBody DiagnosisSubmitFormRequest request) throws InvalidCredentialsException {
		if (authService.isAuthorized(request.getUsername(), request.getToken())) {
			DiagnosisSubmitFormResponse response = new DiagnosisSubmitFormResponse();
			User user = userRepository.findByUsername(request.getUsername());
			
			FormSubmitStatus formSubmitStatus = diagnosisService.acceptSubmittedForm(request.getData(), request.getUsername());
			
			if (formSubmitStatus.equals(FormSubmitStatus.OK)) {
				AnalyseResponseStatus responseStatus = diagnosisService.requestDataAnalyse(user);
				
				if (responseStatus.equals(AnalyseResponseStatus.WARNING)) {
					diagnosisService.requestPerformDiagnosis(user);
				}
				
			} else {
				response.setStatus(formSubmitStatus);			
			}
			
			return response;
		} else {
			throw new InvalidCredentialsException();
		}
	}
	
	@RequestMapping(value = "/perform", method = RequestMethod.POST)
	public DiagnosisSubmitFormResponse performDiagnosis() {
		// TODO mock
		return new DiagnosisSubmitFormResponse();
	}
	
	// TODO ?
	@RequestMapping(value = "/conditions", method = RequestMethod.POST)
	public String getListOfConditions() {
		// TODO mock
		return "diagnosis:getListOfConditions";
	}
	
	@RequestMapping(value = "/rate", method = RequestMethod.POST)
	public String rateDiagnosis() {
		// TODO mock
		return "diagnosis:rateDiagnosis";
	}
	
}
