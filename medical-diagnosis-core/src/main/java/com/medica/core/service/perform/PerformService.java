package com.medica.core.service.perform;

import java.util.List;

import com.medica.core.domain.DiagnosisData;
import com.medica.core.domain.DiagnosisResult;
import com.medica.core.domain.DiagnosisRule;

public interface PerformService {

	public List<DiagnosisResult> performDiagnosis(DiagnosisData currentData, List<DiagnosisRule> rules);
	
}
