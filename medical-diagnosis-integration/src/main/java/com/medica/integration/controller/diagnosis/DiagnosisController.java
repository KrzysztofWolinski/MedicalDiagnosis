package com.medica.integration.controller.diagnosis;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diagnosis")
public class DiagnosisController {

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
