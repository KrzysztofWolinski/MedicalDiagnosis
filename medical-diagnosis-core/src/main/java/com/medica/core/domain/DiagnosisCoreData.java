package com.medica.core.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DiagnosisCoreData {

	private Map<String, CoreDataPiece> dataMap;
	
	private DiagnosisCoreResult diagnosisResult;
	
	private Date dateSubmitted;
	
	public CoreDataPiece getDataPiece(String name) {
		if (dataMap != null) {
			return dataMap.get(name);
		} else {
			return null;
		}
	}
	
	public Map<String, CoreDataPiece> getDataMap() {
		return dataMap;
	}
	
	public void addDataPiece(CoreDataPiece dataPiece) {
		if (this.dataMap == null) {
			this.dataMap = new HashMap<>();
		}
		
		this.dataMap.put(dataPiece.getName(), dataPiece);
	}

	public DiagnosisCoreResult getDiagnosisResult() {
		return diagnosisResult;
	}

	public void setDiagnosisResult(DiagnosisCoreResult diagnosisResult) {
		this.diagnosisResult = diagnosisResult;
	}

	public Date getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	
}
