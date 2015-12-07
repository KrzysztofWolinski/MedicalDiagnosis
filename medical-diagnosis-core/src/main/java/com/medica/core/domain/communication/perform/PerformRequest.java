package com.medica.core.domain.communication.perform;

import java.util.ArrayList;
import java.util.List;

import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.DiagnosisCoreRule;

public class PerformRequest {

	private List<DiagnosisCoreRule> rules;
	
	private DiagnosisCoreData lastData;

	public List<DiagnosisCoreRule> getRules() {
		return rules;
	}

	public void setRules(List<DiagnosisCoreRule> rules) {
		this.rules = rules;
	}
	
	public void addRule(DiagnosisCoreRule rule) {
		if (this.rules == null) {
			this.rules = new ArrayList<DiagnosisCoreRule>();
		}
		this.rules.add(rule);
	}

	public DiagnosisCoreData getLastData() {
		return lastData;
	}

	public void setLastData(DiagnosisCoreData lastData) {
		this.lastData = lastData;
	}
	
}
