package com.medica.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.medica.core.controller.DiagnosisCoreService;
import com.medica.core.controller.impl.DefaultDiagnosisCoreService;
import com.medica.core.service.analyse.AnalyseService;
import com.medica.core.service.analyse.impl.MockAnalyseService;
import com.medica.core.service.learn.LearnService;
import com.medica.core.service.learn.impl.MockLearnService;
import com.medica.core.service.perform.PerformService;
import com.medica.core.service.perform.impl.DefaultPerformService;

@Configuration
public class DiagnosisCoreConfig {

	@Bean
	public DiagnosisCoreService diagnosisCoreService() {
		return new DefaultDiagnosisCoreService();
	}
	
	@Bean
	public AnalyseService analyseService() {
		return new MockAnalyseService();
	}
	
	@Bean
	public LearnService learnService() {
		return new MockLearnService();
	}
	
	@Bean
	public PerformService performService() {
		return new DefaultPerformService();
	}
	
}
