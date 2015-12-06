package com.medica.core.controller.impl;

import java.util.List;

import javax.inject.Inject;

import com.medica.core.controller.DiagnosisService;
import com.medica.core.domain.DiagnosisData;
import com.medica.core.domain.DiagnosisResult;
import com.medica.core.domain.DiagnosisRule;
import com.medica.core.domain.communication.analyse.AnalyseRequest;
import com.medica.core.domain.communication.analyse.AnalyseResponse;
import com.medica.core.domain.communication.analyse.AnalyseResponseStatus;
import com.medica.core.domain.communication.learn.LearnRequest;
import com.medica.core.domain.communication.learn.LearnResponse;
import com.medica.core.domain.communication.perform.PerformRequest;
import com.medica.core.domain.communication.perform.PerformResponse;
import com.medica.core.service.analyse.AnalyseService;
import com.medica.core.service.learn.LearnService;
import com.medica.core.service.perform.PerformService;

public class DiagnosisServiceImpl implements DiagnosisService {

	@Inject
	private LearnService learnService;
	
	@Inject
	private AnalyseService analyseService;
	
	@Inject
	private PerformService performService;
	
	public AnalyseResponse analyse(AnalyseRequest request) {
		AnalyseResponse response = new AnalyseResponse();
		List<DiagnosisData> data = request.getData();
		
		AnalyseResponseStatus responseStatus = analyseService.analyseData(data);
		
		response.setStatus(responseStatus);
		
		return response;
	}

	public LearnResponse learn(LearnRequest request) {
		LearnResponse response = new LearnResponse();
		List<DiagnosisData> data = request.getData();
		
		List<DiagnosisRule> generatedRules = learnService.generateRules(data);
		
		response.setRules(generatedRules);
		
		return response;
	}

	public PerformResponse perform(PerformRequest request) {
		PerformResponse response = new PerformResponse();
		
		DiagnosisData currentData = request.getLastData();
		List<DiagnosisRule> rules = request.getRules();
		
		List<DiagnosisResult> diagnosisResults = performService.performDiagnosis(currentData, rules);
		
		return response;
	}

}
