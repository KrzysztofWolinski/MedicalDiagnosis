package com.medica.integration.controller.history.domain;

import java.util.List;

import com.medica.integration.service.history.domain.HistoryDiagnosesDataBlock;

public class HistoryGetDiagnosesHistoryResponse {

	private List<HistoryDiagnosesDataBlock> data;

	public HistoryGetDiagnosesHistoryResponse() {
	}
	
	public HistoryGetDiagnosesHistoryResponse(List<HistoryDiagnosesDataBlock> data) {
		this.data = data;
	}
	
	public List<HistoryDiagnosesDataBlock> getData() {
		return data;
	}

	public void setData(List<HistoryDiagnosesDataBlock> data) {
		this.data = data;
	}
}
