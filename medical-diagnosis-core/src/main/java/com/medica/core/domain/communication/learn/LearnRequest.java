package com.medica.core.domain.communication.learn;

import java.util.List;

import com.medica.core.domain.DiagnosisCoreData;

public class LearnRequest {
	
	private List<DiagnosisCoreData> data;
	
	public List<DiagnosisCoreData> getData() {
		return data;
	}

	public void setData(List<DiagnosisCoreData> data) {
		this.data = data;
	}
}
