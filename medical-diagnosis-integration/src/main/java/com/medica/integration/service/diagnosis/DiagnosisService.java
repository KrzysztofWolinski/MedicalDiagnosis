package com.medica.integration.service.diagnosis;

import com.medica.integration.service.diagnosis.domain.DiagnosisForm;

public interface DiagnosisService {

	public DiagnosisForm getForm(); 
	
	public void acceptSubmittedForm(DiagnosisForm form);
	
	// TODO perform diagnosis
	
	// TODO rate diagnosis
}
