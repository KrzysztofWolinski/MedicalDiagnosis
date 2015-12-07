package com.medica.core.controller;

import com.medica.core.domain.communication.analyse.AnalyseRequest;
import com.medica.core.domain.communication.analyse.AnalyseResponse;
import com.medica.core.domain.communication.learn.LearnRequest;
import com.medica.core.domain.communication.learn.LearnResponse;
import com.medica.core.domain.communication.perform.PerformRequest;
import com.medica.core.domain.communication.perform.PerformResponse;

public interface DiagnosisCoreService {

	public AnalyseResponse analyse(AnalyseRequest request);
	
	public LearnResponse learn(LearnRequest request);
	
	public PerformResponse perform(PerformRequest request);
	
}
