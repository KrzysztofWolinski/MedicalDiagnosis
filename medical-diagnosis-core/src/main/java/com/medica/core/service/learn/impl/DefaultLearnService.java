package com.medica.core.service.learn.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.medica.core.domain.CoreDataPiece;
import com.medica.core.domain.DataPieceValueType;
import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.learn.NumberRange;
import com.medica.core.domain.learn.Range;
import com.medica.core.domain.learn.StringRange;
import com.medica.core.domain.rule.DiagnosisCoreRule;
import com.medica.core.service.learn.LearnService;

public class DefaultLearnService implements LearnService {

	private static final String TOTAL_COUNT_KEY = "TOTAL";
	
	private static final int MAX_DIV_COUNT = 10;
	private static final int INIT_DIV_COUNT = 2;
	private static final float TARGET_QUALIFYING_SHARE = 0.75f;
	private static final float MIN_QUALIFYING_SHARE = 0.5f;
	
	@Override
	public List<DiagnosisCoreRule> generateRules(List<DiagnosisCoreData> allDataList) {
		List<DiagnosisCoreData> filteredData = filterOutNonRatedData(allDataList);
		int recordCount = filteredData.size();
		
		Map<String, DataPieceValueType> attibutesInfo = getAttibutesInfo(filteredData);
		Set<String> conditions = getConditions(filteredData);
		
		Map<String, Set<Range>> dataRanges = new HashMap<String, Set<Range>>();
		
		for (Map.Entry<String, DataPieceValueType> attributeInfo : attibutesInfo.entrySet()) {
			
			if (attributeInfo.getValue().equals(DataPieceValueType.STRING)) {
				Set<String> values = filteredData.stream().map(d -> d.getDataPiece(attributeInfo.getKey()).getValue()).distinct().collect(Collectors.toSet());
				Set<Range> ranges = values.stream().map(e -> new StringRange(e)).collect(Collectors.toSet());

				ranges.forEach(range -> {
					String currentAttributeValue = ((StringRange) range).getName();
					
					conditions.forEach(condition -> {
						long dataCount  = filteredData.stream().filter(d -> {
							boolean hasCondition = d.getDiagnosisResult().getConditionProbability().containsKey(condition); 
							boolean hasValue = d.getDataPiece(attributeInfo.getKey()).getValue().equals(currentAttributeValue);
							
							return hasCondition && hasValue;
						}).count();
						
						range.addCountValue(condition, dataCount);
					});
					
					long totalCount = filteredData.stream().filter(d -> d.getDataPiece(attributeInfo.getKey()).getValue().equals(currentAttributeValue)).count();
					range.addCountValue(TOTAL_COUNT_KEY, totalCount);
				});
				
				dataRanges.put(attributeInfo.getKey(), ranges);
			} else {
				List<Float> values = filteredData.stream().map(d -> Float.valueOf(d.getDataPiece(attributeInfo.getKey()).getValue())).collect(Collectors.toList());
				Float minValue = values.stream().min((v1, v2) -> v1 < v2 ? -1 : v1 > v2 ? 1 : 0).get();
				Float maxValue = values.stream().max((v1, v2) -> v1 < v2 ? -1 : v1 > v2 ? 1 : 0).get();
				Float valueSpan = maxValue - minValue;
				
				int currentRangeCount = INIT_DIV_COUNT;
				float currentMinShare = TARGET_QUALIFYING_SHARE;
				Set<Range> ranges = new HashSet<Range>();
				
				
				while (currentMinShare > MIN_QUALIFYING_SHARE) {
					for (int i = 0; i < currentRangeCount; i++) {
						NumberRange range = new NumberRange();
						
						range.setMinValue(minValue + (i * (valueSpan / currentRangeCount)));
						range.setMaxValue(range.getMinValue() + (valueSpan / currentRangeCount));
						
						ranges.add(range);
					}
					
					ranges.forEach(range -> {
						conditions.forEach(condition -> {
							long dataCount  = filteredData.stream().filter(d -> {
								Float currentValue = Float.parseFloat(d.getDataPiece(attributeInfo.getKey()).getValue());
								
								boolean hasCondition = d.getDiagnosisResult().getConditionProbability().containsKey(condition); 
								boolean hasValue = ((NumberRange) range).isInRange(currentValue);
								
								return hasCondition && hasValue;
							}).count();
							
							range.addCountValue(condition, dataCount);
						});
						
						long totalCount = filteredData.stream().filter(d -> {
							Float currentValue = Float.parseFloat(d.getDataPiece(attributeInfo.getKey()).getValue());
							return ((NumberRange) range).isInRange(currentValue);
						}).count();
						
						range.addCountValue(TOTAL_COUNT_KEY, totalCount);
					});
					
				}
				
				dataRanges.put(attributeInfo.getKey(), ranges);
			}
			
			
		}
		
		return null;
	}

	private List<DiagnosisCoreData> filterOutNonRatedData(List<DiagnosisCoreData> allDataList) {
		List<DiagnosisCoreData> filteredData = allDataList.stream()
				.filter(d -> ((d.getDiagnosisResult() != null) && (d.getDiagnosisResult().isRated()))).collect(Collectors.toList());
		
		return filteredData;
	}
	
	private Set<String> getConditions(List<DiagnosisCoreData> allDataList) {
		Set<String> conditionsSet = allDataList.stream().flatMap(d -> d.getDiagnosisResult().getConditionProbability().keySet().stream()).collect(Collectors.toSet());
		return conditionsSet;
	}
	
	private Map<String, DataPieceValueType> getAttibutesInfo(List<DiagnosisCoreData> filteredData) {
		if (!filteredData.isEmpty()) {
			DiagnosisCoreData referenceData = filteredData.get(0);
			Set<String> names = referenceData.getDataMap().keySet();
			
			Map<String, DataPieceValueType> map = new HashMap<>();
			for (String name : names) {
				CoreDataPiece dataPiece = referenceData.getDataPiece(name);
				
				try {
					Float.valueOf(dataPiece.getValue());
					map.put(name, DataPieceValueType.NUMBER);
				} catch (NumberFormatException e) {
					map.put(name, DataPieceValueType.STRING);
				}
			}
			
			return map;
		} else {
			return new HashMap<String, DataPieceValueType>();
		}
	}
	
	private List<DiagnosisCoreRule> convertRangesToRules(Map<String, Set<Range>> ranges) {
		List<DiagnosisCoreRule> rules = new ArrayList<DiagnosisCoreRule>();
		
		return rules;
	}
	
}
