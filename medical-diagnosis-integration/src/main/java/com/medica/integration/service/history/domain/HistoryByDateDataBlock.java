package com.medica.integration.service.history.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryByDateDataBlock {

	private Date date;
	
	private List<HistoryByDateDataPiece> data = new ArrayList<HistoryByDateDataPiece>();

	public Date getDate() {
		return date;
	}

	public void setDate(Date dateSubtmitted) {
		this.date = dateSubtmitted;
	}

	public List<HistoryByDateDataPiece> getData() {
		return data;
	}

	public void setData(List<HistoryByDateDataPiece> data) {
		this.data = data;
	}
	
	
}
