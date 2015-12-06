package com.medica.core.domain.communication.perform;

import java.util.List;

import com.medica.core.domain.DiagnosisResult;

public class PerformResponse {

	private List<DiagnosisResult> resultList;

	public List<DiagnosisResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<DiagnosisResult> resultList) {
		this.resultList = resultList;
	}
	
}
