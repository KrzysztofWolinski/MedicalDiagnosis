package com.medica.core.domain.communication.learn;

import java.util.List;

import com.medica.core.domain.DiagnosisCoreRule;

public class LearnResponse {

	private List<DiagnosisCoreRule> rules;

	public List<DiagnosisCoreRule> getRules() {
		return rules;
	}

	public void setRules(List<DiagnosisCoreRule> rules) {
		this.rules = rules;
	}
	
}
