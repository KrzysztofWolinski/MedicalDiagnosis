package com.medica.integration.controller.history.domain;

import java.util.List;

import com.medica.integration.service.history.domain.HistoryByNameDataBlock;

public class HistoryGetDataByNameResponse {

	private List<HistoryByNameDataBlock> data;

	public HistoryGetDataByNameResponse() {
	}
	
	public HistoryGetDataByNameResponse(List<HistoryByNameDataBlock> data) {
		this.data = data;
	}
	
	public List<HistoryByNameDataBlock> getData() {
		return data;
	}

	public void setData(List<HistoryByNameDataBlock> data) {
		this.data = data;
	}
	
}
