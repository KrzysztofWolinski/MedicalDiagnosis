package com.medica.core.domain.communication.perform;

import java.util.ArrayList;
import java.util.List;

import com.medica.core.domain.DiagnosisData;
import com.medica.core.domain.DiagnosisRule;

public class PerformRequest {

	private List<DiagnosisRule> rules;
	
	private DiagnosisData lastData;

	public List<DiagnosisRule> getRules() {
		return rules;
	}

	public void setRules(List<DiagnosisRule> rules) {
		this.rules = rules;
	}
	
	public void addRule(DiagnosisRule rule) {
		if (this.rules == null) {
			this.rules = new ArrayList<DiagnosisRule>();
		}
		this.rules.add(rule);
	}

	public DiagnosisData getLastData() {
		return lastData;
	}

	public void setLastData(DiagnosisData lastData) {
		this.lastData = lastData;
	}
	
}
