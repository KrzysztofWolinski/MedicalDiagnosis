package com.medica.integration.service.history;

import java.util.List;

import com.medica.integration.service.history.domain.HistoryByDateDataPiece;
import com.medica.integration.service.history.domain.HistoryByNameDataPiece;
import com.medica.integration.service.history.domain.HistoryDiagnosesDataPiece;

public interface HistoryService {
	
	public List<HistoryByDateDataPiece> getDataByDate(String username);
	
	public List<HistoryByNameDataPiece> getDataByName(String username);
	
	public List<HistoryDiagnosesDataPiece> getDiagnosesData(String username);
}
