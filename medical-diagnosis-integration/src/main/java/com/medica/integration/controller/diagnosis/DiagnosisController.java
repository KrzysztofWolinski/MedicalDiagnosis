package com.medica.integration.controller.diagnosis;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.medica.integration.controller.diagnosis.domain.DiagnosisFormRequestDto;
import com.medica.integration.service.auth.AuthService;
import com.medica.integration.service.diagnosis.DiagnosisService;
import com.medica.integration.service.diagnosis.domain.DiagnosisForm;

@RestController
@RequestMapping("/diagnosis")
public class DiagnosisController {

	@Inject
	DiagnosisService diagnosisService;
	
	@Inject
	AuthService authService;
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	@ResponseBody
    public DiagnosisForm getForm(@RequestBody DiagnosisFormRequestDto request) {  	
		if (authService.isAuthorized(request.getUsername(), request.getToken())) {
			return this.diagnosisService.getForm();
		} else {
			// TODO
			return null;
		}
	}
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String submitData() {
		// TODO mock
		return "diagnosis:submitData";
	}
	
	@RequestMapping(value = "/perform", method = RequestMethod.GET)
	public String performDiagnosis() {
		// TODO mock
		return "diagnosis:performDiagnosis";
	}
	
	@RequestMapping(value = "/rate", method = RequestMethod.POST)
	public String rateDiagnosis() {
		// TODO mock
		return "diagnosis:rateDiagnosis";
	}
	
}
