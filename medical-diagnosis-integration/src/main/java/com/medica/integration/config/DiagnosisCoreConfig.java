package com.medica.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.medica.core.service.analyse.AnalyseService;
import com.medica.core.service.analyse.impl.DefaultAnalyseService;
import com.medica.core.service.learn.LearnService;
import com.medica.core.service.learn.impl.DefaultLearnService;
import com.medica.core.service.perform.PerformService;
import com.medica.core.service.perform.impl.DefaultPerformService;
import com.medica.integration.service.diagnosis.DiagnosisService;
import com.medica.integration.service.diagnosis.impl.DiagnosisServiceImpl;

@Configuration
public class DiagnosisCoreConfig {

	@Bean
	public DiagnosisService diagnosisService() {
		return new DiagnosisServiceImpl();
	}
	
	@Bean
	public AnalyseService analyseService() {
		return new DefaultAnalyseService();
	}
	
	@Bean
	public LearnService learnService() {
		return new DefaultLearnService();
	}
	
	@Bean
	public PerformService performService() {
		return new DefaultPerformService();
	}
	
}
