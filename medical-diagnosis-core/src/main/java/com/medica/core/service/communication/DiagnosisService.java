package com.medica.core.service.communication;

import com.medica.core.domain.communication.AnalyseRequest;
import com.medica.core.domain.communication.AnalyseResponse;
import com.medica.core.domain.communication.LearnRequest;
import com.medica.core.domain.communication.LearnResponse;

public interface DiagnosisService {

	public AnalyseResponse analyse(AnalyseRequest request);
	
	public LearnResponse learn(LearnRequest request);
	
}
