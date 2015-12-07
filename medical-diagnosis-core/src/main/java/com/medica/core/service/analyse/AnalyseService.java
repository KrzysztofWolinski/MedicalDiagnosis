package com.medica.core.service.analyse;

import java.util.List;

import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.communication.analyse.AnalyseResponseStatus;

public interface AnalyseService {

	public AnalyseResponseStatus analyseData(List<DiagnosisCoreData> recentUserDataList);
	
}
