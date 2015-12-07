package com.medica.core.service.learn;

import java.util.List;

import com.medica.core.domain.DiagnosisCoreResult;
import com.medica.core.domain.DiagnosisCoreRule;

public interface LearnService {

	public List<DiagnosisCoreRule> generateRules(List<DiagnosisCoreResult> allDataList);
	
}
