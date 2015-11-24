package com.medica.integration.controller.history.domain;

import java.util.List;

import com.medica.integration.service.history.domain.HistoryByNameDataPiece;

public class HistoryGetDataByNameResponse {

	private List<HistoryByNameDataPiece> data;

	public HistoryGetDataByNameResponse() {
	}
	
	public HistoryGetDataByNameResponse(List<HistoryByNameDataPiece> data) {
		this.data = data;
	}
	
	public List<HistoryByNameDataPiece> getData() {
		return data;
	}

	public void setData(List<HistoryByNameDataPiece> data) {
		this.data = data;
	}
	
}
