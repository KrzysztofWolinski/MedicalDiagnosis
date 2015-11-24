package com.medica.integration.service.history.domain;

import java.util.ArrayList;
import java.util.List;

public class HistoryByNameDataBlock {

	private String name;
	
	private List<HistoryByNameDataPiece> dataPieces;
	
	public HistoryByNameDataBlock() {
		this.dataPieces = new ArrayList<HistoryByNameDataPiece>();
	}
	
	public HistoryByNameDataBlock(String name) {
		this.name = name;
		this.dataPieces = new ArrayList<HistoryByNameDataPiece>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<HistoryByNameDataPiece> getDataPieces() {
		return dataPieces;
	}

	public void setDataPieces(List<HistoryByNameDataPiece> dataPieces) {
		this.dataPieces = dataPieces;
	}
	
	public HistoryByNameDataBlock addDataPiece(HistoryByNameDataPiece dataPiece) {
		this.dataPieces.add(dataPiece);
		return this;
	}
	
}
