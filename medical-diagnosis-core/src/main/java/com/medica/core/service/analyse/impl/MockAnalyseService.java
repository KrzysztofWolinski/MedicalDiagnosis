package com.medica.core.service.analyse.impl;

import java.util.List;

import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.communication.analyse.AnalyseResponseStatus;
import com.medica.core.service.analyse.AnalyseService;

public class MockAnalyseService implements AnalyseService {

	@Override
	public AnalyseResponseStatus analyseData(List<DiagnosisCoreData> recentUserDataList) {
		return AnalyseResponseStatus.WARNING;
	}

}
