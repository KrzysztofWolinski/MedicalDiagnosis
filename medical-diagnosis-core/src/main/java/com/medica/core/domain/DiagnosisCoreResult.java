package com.medica.core.domain;

import java.util.HashMap;
import java.util.Map;

public class DiagnosisCoreResult {

	private DiagnosisCoreData providedData;
	
	private Map<String, Integer> conditionProbability;

	public DiagnosisCoreResult() {
		this.conditionProbability = new HashMap<String, Integer>();
	}
	
	public DiagnosisCoreData getProvidedData() {
		return providedData;
	}

	public void setProvidedData(DiagnosisCoreData providedData) {
		this.providedData = providedData;
	}

	public Map<String, Integer> getConditionProbability() {
		return conditionProbability;
	}

	public void addConditionProbability(String conditionName, Integer probability) {
		if (probability < 0) {
			probability = 0;
		} else if (probability > 100) {
			probability = 100;
		}
		
		this.conditionProbability.put(conditionName, probability);
	}

}
