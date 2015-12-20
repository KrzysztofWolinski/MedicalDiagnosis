package com.medica.integration.service.converters;

import java.util.ArrayList;
import java.util.List;

import com.medica.core.domain.CoreDataPiece;
import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.DiagnosisCoreResult;
import com.medica.integration.domain.diagnosis.DataPiece;
import com.medica.integration.domain.diagnosis.DiagnosisData;


public class DiagnosisDataConverter {
	
	public static List<DiagnosisCoreData> convertToDto(List<DiagnosisData> inputData) {
		List<DiagnosisCoreData> output = new ArrayList<DiagnosisCoreData>();
		
		for (DiagnosisData data : inputData) {
			output.add(convertToDto(data));
		}
		
		return output;
	}
	
	public static DiagnosisCoreData convertToDto(DiagnosisData inputData) {
		DiagnosisCoreData output = new DiagnosisCoreData();
		
		output.setDateSubmitted(inputData.getDateSubtmitted());
		
		DiagnosisCoreResult convertedResult = DiagnosisResultConverter.convertToDto(inputData.getDiagnosisResult());
		output.setDiagnosisResult(convertedResult);
		
		if (inputData != null) {
			for (DataPiece dataPiece : inputData.getData()) {
				CoreDataPiece convertedDataPiece = new CoreDataPiece();
				convertedDataPiece.setName(dataPiece.getName());
				convertedDataPiece.setValue(dataPiece.getValue());
				
				output.addDataPiece(convertedDataPiece);
			}
		}
		
		return output;
	}
	
}
