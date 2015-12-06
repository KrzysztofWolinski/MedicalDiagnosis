package com.medica.core.domain.communication.analyse;

import java.util.ArrayList;
import java.util.List;

import com.medica.core.domain.DiagnosisData;

public class AnalyseRequest {
	
	private List<DiagnosisData> dataList;

	public List<DiagnosisData> getData() {
		return dataList;
	}

	public void setData(List<DiagnosisData> data) {
		this.dataList = data;
	}
	
	public void addData(DiagnosisData data) {
		if (this.dataList == null) {
			this.dataList = new ArrayList<DiagnosisData>();
		}
		this.dataList.add(data);
	}
	
}
