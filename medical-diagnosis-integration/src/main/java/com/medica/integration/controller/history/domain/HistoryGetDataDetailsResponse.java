package com.medica.integration.controller.history.domain;

import com.medica.integration.service.history.domain.HistoryDataDetails;

public class HistoryGetDataDetailsResponse {

	private HistoryDataDetails data;

	public HistoryDataDetails getData() {
		return data;
	}

	public void setData(HistoryDataDetails data) {
		this.data = data;
	}

}
