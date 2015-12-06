package com.medica.core.service.learn;

import java.util.List;

import com.medica.core.domain.DiagnosisData;
import com.medica.core.domain.DiagnosisRule;

public interface LearnService {

	public List<DiagnosisRule> generateRules(List<DiagnosisData> allDataList);
	
}
