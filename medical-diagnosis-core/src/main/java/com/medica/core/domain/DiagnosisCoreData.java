package com.medica.core.domain;

import java.util.HashMap;
import java.util.Map;

public class DiagnosisCoreData {

	Map<String, CoreDataPiece> dataMap;
	
	public CoreDataPiece getDataPiece(String name) {
		if (dataMap != null) {
			return dataMap.get(name);
		} else {
			return null;
		}
	}
	
	public void addDataPiece(CoreDataPiece dataPiece) {
		if (this.dataMap == null) {
			this.dataMap = new HashMap<>();
		}
		
		this.dataMap.put(dataPiece.getName(), dataPiece);
	}
}
