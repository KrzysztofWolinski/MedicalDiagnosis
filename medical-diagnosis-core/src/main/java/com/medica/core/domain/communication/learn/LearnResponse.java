package com.medica.core.domain.communication.learn;

import java.util.List;

import com.medica.core.domain.DiagnosisRule;

public class LearnResponse {

	private List<DiagnosisRule> ruleList;

	public List<DiagnosisRule> getData() {
		return ruleList;
	}

	public void setRules(List<DiagnosisRule> ruleList) {
		this.ruleList = ruleList;
	}
	
}
