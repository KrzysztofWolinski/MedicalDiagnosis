package com.medica.core.service.analyse.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.medica.core.domain.CoreByNameDataPiece;
import com.medica.core.domain.CoreDataPiece;
import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.communication.analyse.AnalyseResponseStatus;
import com.medica.core.service.analyse.AnalyseService;

public class DefaultAnalyseService implements AnalyseService {

	@Override
	public AnalyseResponseStatus analyseData(List<DiagnosisCoreData> recentUserDataList) {

		// Filter out the data with diagnosed conditions, and not rated
		recentUserDataList = recentUserDataList.stream().filter(d -> 
				d.getDiagnosisResult() != null && 
				d.getDiagnosisResult().getConditionProbability().isEmpty() &&
				d.getDiagnosisResult().isRated()
			).collect(Collectors.toList());
		
		if (recentUserDataList.size() > 1) {
			
			// Get latest data
			DiagnosisCoreData latestData = recentUserDataList.stream().max((d1, d2) -> d1.getDateSubmitted().compareTo(d2.getDateSubmitted())).get();
			recentUserDataList.remove(latestData);
			
			// Converts all the data
			List<CoreByNameDataPiece> ungroupedList = recentUserDataList.stream().flatMap(d -> {
				Date dateSubmitted = d.getDateSubmitted();
				
				return d.getDataMap().values().stream().map(dp -> new CoreByNameDataPiece(dp.getName(), dateSubmitted, dp.getValue()));
			}).map(o -> (CoreByNameDataPiece) o).collect(Collectors.toList());
			
			// Groups by name
			Map<Object, List<CoreByNameDataPiece>> dataMap = ungroupedList.stream().collect(Collectors.groupingBy(dp -> dp.getName()));
			
			// Analyse every parameter list separately
			for (Map.Entry<Object, List<CoreByNameDataPiece>> dataEntry : dataMap.entrySet()) {
				
				CoreDataPiece latestValue = latestData.getDataPiece((String) dataEntry.getKey());
				
				if (latestValue != null) {
					if (isStandingOut(latestValue.getValue(), dataEntry.getValue())) {
						return AnalyseResponseStatus.WARNING;
					}
				}
			}
			
		} 
		
		return AnalyseResponseStatus.OK;
	}
	
	private boolean isStandingOut(String value, List<CoreByNameDataPiece> data) {
		if (data.isEmpty()) {
			return true;
		} 
		
		
		Float floatValue = convertToFloat(value);
		
		if (floatValue != null) {
		
			Float minValue = Float.valueOf(data.stream().min(Comparator.comparing(f -> Float.valueOf(f.getValue()))).get().getValue());
			Float maxValue = Float.valueOf(data.stream().max(Comparator.comparing(f -> Float.valueOf(f.getValue()))).get().getValue());
			
			if ((floatValue < minValue) || (floatValue > maxValue)) {
				return true;
			} else {
				return false;
			}
		} else {
			Set<String> values = data.stream().map(v -> v.getValue()).collect(Collectors.toSet());
			
			return !values.contains(value);
		}
	}
	
	private Float convertToFloat(String value) {
		try {
			Float result = Float.valueOf(value);
			return result;
		} catch(NumberFormatException e) {
			return null;
		}
	}

}
