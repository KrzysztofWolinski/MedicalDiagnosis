package com.medica.core.domain.communication.learn;

import java.util.ArrayList;
import java.util.List;

import com.medica.core.domain.DiagnosisCoreResult;

public class LearnRequest {
	
	private List<DiagnosisCoreResult> results;

	public List<DiagnosisCoreResult> getResults() {
		return results;
	}

	public void setResults(List<DiagnosisCoreResult> results) {
		this.results = results;
	}
	
	public void addResult(DiagnosisCoreResult result) {
		if (this.results == null) {
			this.results = new ArrayList<DiagnosisCoreResult>();
		}
		this.results.add(result);
	}
}
