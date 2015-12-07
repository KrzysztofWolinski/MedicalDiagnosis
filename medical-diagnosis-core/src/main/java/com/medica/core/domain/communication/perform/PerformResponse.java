package com.medica.core.domain.communication.perform;

import java.util.List;

import com.medica.core.domain.DiagnosisCoreResult;

public class PerformResponse {

	private List<DiagnosisCoreResult> resultList;

	public List<DiagnosisCoreResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<DiagnosisCoreResult> resultList) {
		this.resultList = resultList;
	}
	
}
