package com.medica.core.service.perform;

import java.util.List;

import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.DiagnosisCoreResult;
import com.medica.core.domain.rule.DiagnosisCoreRule;

public interface PerformService {

	public DiagnosisCoreResult performDiagnosis(DiagnosisCoreData currentData, List<DiagnosisCoreRule> rules);
	
}
