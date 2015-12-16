package com.medica.integration.service.history;

import java.util.List;

import com.medica.integration.controller.history.domain.HistoryGetDataDetailsResponse;
import com.medica.integration.service.history.domain.HistoryByDateDataBlock;
import com.medica.integration.service.history.domain.HistoryByNameDataBlock;
import com.medica.integration.service.history.domain.HistoryConditionProbability;
import com.medica.integration.service.history.domain.HistoryDiagnosesDataBlock;

public interface HistoryService {
	
	public List<HistoryByDateDataBlock> getDataByDate(String username);
	
	public List<HistoryByNameDataBlock> getDataByName(String username);
	
	public List<HistoryDiagnosesDataBlock> getDiagnosesData(String username);
	
	public HistoryGetDataDetailsResponse getDataDetails(String username, Long id);
	
	public void reviewDiagnosisDataResults(String username, Long id, List<HistoryConditionProbability> newConditionProbabilities);
	
	public void deleteSingliDataSet(Long id, String username);
}
