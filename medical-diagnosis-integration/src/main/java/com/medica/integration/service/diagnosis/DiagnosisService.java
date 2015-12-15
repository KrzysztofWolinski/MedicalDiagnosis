package com.medica.integration.service.diagnosis;

import com.medica.core.domain.communication.analyse.AnalyseResponseStatus;
import com.medica.integration.controller.diagnosis.domain.FormSubmitStatus;
import com.medica.integration.domain.user.User;
import com.medica.integration.service.diagnosis.domain.DiagnosisForm;

public interface DiagnosisService {

	public DiagnosisForm getForm(); 
	
	public FormSubmitStatus acceptSubmittedForm(DiagnosisForm form, String username);
	
	public AnalyseResponseStatus requestDataAnalyse(User user);
	
	public void requestPerformDiagnosis(User user);
	
}
