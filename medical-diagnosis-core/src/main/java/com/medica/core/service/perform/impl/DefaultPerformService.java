package com.medica.core.service.perform.impl;

import java.util.List;

import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.DiagnosisCoreResult;
import com.medica.core.domain.rule.DiagnosisCoreRule;
import com.medica.core.service.perform.PerformService;

public class DefaultPerformService implements PerformService {

	@Override
	public DiagnosisCoreResult performDiagnosis(DiagnosisCoreData currentData, List<DiagnosisCoreRule> rules) {
		DiagnosisCoreResult result = new DiagnosisCoreResult();
		result.setProvidedData(currentData);

		for (DiagnosisCoreRule rule : rules) {
			boolean evaluationResult = rule.evaluate(currentData);
			
			if (evaluationResult) {
				result.addConditionProbability(rule.getDecision(), rule.getProbability());
			}
		}
		
		return result;
	}

}
