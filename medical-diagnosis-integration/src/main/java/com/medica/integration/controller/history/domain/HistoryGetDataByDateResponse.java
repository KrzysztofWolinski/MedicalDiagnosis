package com.medica.integration.controller.history.domain;

import java.util.List;

import com.medica.integration.service.history.domain.HistoryByDateDataBlock;

public class HistoryGetDataByDateResponse {

	private List<HistoryByDateDataBlock> data;

	public HistoryGetDataByDateResponse() {
	}
	
	public HistoryGetDataByDateResponse(List<HistoryByDateDataBlock> data) {
		this.data = data;
	}
	
	public List<HistoryByDateDataBlock> getData() {
		return data;
	}

	public void setData(List<HistoryByDateDataBlock> data) {
		this.data = data;
	}
		
}
