package com.medica.integration.controller.history.domain;

import java.util.List;

import com.medica.integration.service.history.domain.HistoryByDateDataPiece;

public class HistoryGetDataByDateResponse {

	private List<HistoryByDateDataPiece> data;

	public HistoryGetDataByDateResponse() {
	}
	
	public HistoryGetDataByDateResponse(List<HistoryByDateDataPiece> data) {
		this.data = data;
	}
	
	public List<HistoryByDateDataPiece> getData() {
		return data;
	}

	public void setData(List<HistoryByDateDataPiece> data) {
		this.data = data;
	}
		
}
