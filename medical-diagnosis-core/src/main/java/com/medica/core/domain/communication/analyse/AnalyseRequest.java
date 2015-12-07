package com.medica.core.domain.communication.analyse;

import java.util.ArrayList;
import java.util.List;

import com.medica.core.domain.DiagnosisCoreData;

public class AnalyseRequest {
	
	private List<DiagnosisCoreData> dataList;

	public List<DiagnosisCoreData> getData() {
		return dataList;
	}

	public void setData(List<DiagnosisCoreData> data) {
		this.dataList = data;
	}
	
	public void addData(DiagnosisCoreData data) {
		if (this.dataList == null) {
			this.dataList = new ArrayList<DiagnosisCoreData>();
		}
		this.dataList.add(data);
	}
	
}
