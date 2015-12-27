package com.medica.core.controller.impl;

import java.util.List;

import javax.inject.Inject;

import com.medica.core.controller.DiagnosisCoreService;
import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.DiagnosisCoreResult;
import com.medica.core.domain.communication.analyse.AnalyseRequest;
import com.medica.core.domain.communication.analyse.AnalyseResponse;
import com.medica.core.domain.communication.analyse.AnalyseResponseStatus;
import com.medica.core.domain.communication.learn.LearnRequest;
import com.medica.core.domain.communication.learn.LearnResponse;
import com.medica.core.domain.communication.perform.PerformRequest;
import com.medica.core.domain.communication.perform.PerformResponse;
import com.medica.core.domain.rule.DiagnosisCoreRule;
import com.medica.core.service.analyse.AnalyseService;
import com.medica.core.service.learn.LearnService;
import com.medica.core.service.perform.PerformService;

public class DefaultDiagnosisCoreService implements DiagnosisCoreService {

	@Inject
	private LearnService learnService;
	
	@Inject
	private AnalyseService analyseService;
	
	@Inject
	private PerformService performService;
	
	public AnalyseResponse analyse(AnalyseRequest request) {
		AnalyseResponse response = new AnalyseResponse();
		List<DiagnosisCoreData> data = request.getData();
		
		AnalyseResponseStatus responseStatus = analyseService.analyseData(data);
		
		response.setStatus(responseStatus);
		
		return response;
	}

	public LearnResponse learn(LearnRequest request) {
		LearnResponse response = new LearnResponse();
		List<DiagnosisCoreData> inputData = request.getData();
		
		List<DiagnosisCoreRule> generatedRules = learnService.generateRules(inputData);
		
		response.setRules(generatedRules);
		
		return response;
	}

	public PerformResponse perform(PerformRequest request) {
		PerformResponse response = new PerformResponse();
		
		DiagnosisCoreData currentData = request.getLastData();
		List<DiagnosisCoreRule> rules = request.getRules();
		
		DiagnosisCoreResult diagnosisResult = performService.performDiagnosis(currentData, rules);
		
		response.setResult(diagnosisResult);
		
		return response;
	}

}
