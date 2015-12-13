package com.medica.integration.service.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.DiagnosisCoreResult;
import com.medica.integration.domain.diagnosis.ConditionProbability;
import com.medica.integration.domain.diagnosis.DiagnosisResult;

public class DiagnosisResultConverter {

	public static List<DiagnosisResult> convertToDao(List<DiagnosisCoreResult> inputResults) {
		List<DiagnosisResult> output = new ArrayList<DiagnosisResult>();
		
		for (DiagnosisCoreResult result : inputResults) {
			output.add(convertToDao(result));
		}
		
		return output;
	}
	
	public static DiagnosisResult convertToDao(DiagnosisCoreResult inputResult) {
		DiagnosisResult output = new DiagnosisResult();
		
		List<ConditionProbability> conditionList = new ArrayList<ConditionProbability>();
		for (Map.Entry<String, Integer> entry : inputResult.getConditionProbability().entrySet()) {
			ConditionProbability conditionProbability = new ConditionProbability();
			conditionProbability.setDiseaseName(entry.getKey());
			conditionProbability.setProbability(entry.getValue());
			
			conditionList.add(conditionProbability);
		}
		
		output.setConditionsProbablity(conditionList);
		
		return output;
	}
	
	public static List<DiagnosisCoreResult> convertToDto(List<DiagnosisResult> inputResults) {
		List<DiagnosisCoreResult> output = new ArrayList<DiagnosisCoreResult>();
		
		for (DiagnosisResult result : inputResults) {
			output.add(convertToDto(result));
		}
		
		return output;
	}
	
	public static DiagnosisCoreResult convertToDto(DiagnosisResult result) {
		DiagnosisCoreResult convertedResult = new DiagnosisCoreResult();
		
		DiagnosisCoreData convertedData = DiagnosisDataConverter.convertToDto(result.getDiagnosedData());
		
		convertedResult.setProvidedData(convertedData);
		
		if (result.getConditionsProbablity() != null) {
			for (ConditionProbability conditionProbability : result.getConditionsProbablity()) {
				convertedResult.addConditionProbability(conditionProbability.getDiseaseName(), conditionProbability.getProbability());
			}
		}
		
		return convertedResult;
	}
}
