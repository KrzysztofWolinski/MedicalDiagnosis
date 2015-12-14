package com.medica.integration.service.history.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryByDateDataBlock {

	private Long dataId;
	
	private Date date;
	
	private List<HistoryByDateDataPiece> data = new ArrayList<HistoryByDateDataPiece>();

	private boolean rated;
	
	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}
	
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

	public boolean isRated() {
		return rated;
	}

	public void setRated(boolean rated) {
		this.rated = rated;
	}
	
}
