package com.medica.integration.controller.history.domain;

import java.util.List;

import com.medica.integration.service.history.domain.HistoryDiagnosesDataPiece;

public class HistoryGetDiagnosesHistoryResponse {

	private List<HistoryDiagnosesDataPiece> data;

	public HistoryGetDiagnosesHistoryResponse() {
	}
	
	public HistoryGetDiagnosesHistoryResponse(List<HistoryDiagnosesDataPiece> data) {
		this.data = data;
	}
	
	public List<HistoryDiagnosesDataPiece> getData() {
		return data;
	}

	public void setData(List<HistoryDiagnosesDataPiece> data) {
		this.data = data;
	}
}
