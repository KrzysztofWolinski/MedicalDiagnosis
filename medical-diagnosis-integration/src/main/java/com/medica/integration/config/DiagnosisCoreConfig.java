package com.medica.integration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.medica.core.controller.DiagnosisCoreService;
import com.medica.core.controller.impl.DefaultDiagnosisCoreService;
import com.medica.core.service.analyse.AnalyseService;
import com.medica.core.service.analyse.impl.DefaultAnalyseService;
import com.medica.core.service.learn.LearnService;
import com.medica.core.service.learn.impl.DefaultLearnService;
import com.medica.core.service.perform.PerformService;
import com.medica.core.service.perform.impl.DefaultPerformService;

@Configuration
public class DiagnosisCoreConfig {

	@Value("${diagnosis.learn.div_count}")
	private int DIV_COUNT;
	
	@Value("${diagnosis.learn.target_qualifying_share}")
	private float TARGET_QUALIFYING_SHARE;
	
	@Value("${diagnosis.learn.min_qualifying_share}")
	private float MIN_QUALIFYING_SHARE;
	
	@Bean
	public DiagnosisCoreService diagnosisCoreService() {
		return new DefaultDiagnosisCoreService();
	}
	
	@Bean
	public AnalyseService analyseService() {
		return new DefaultAnalyseService();
	}
	
	@Bean
	public LearnService learnService() {
		return new DefaultLearnService(DIV_COUNT, TARGET_QUALIFYING_SHARE, MIN_QUALIFYING_SHARE);
	}
	
	@Bean
	public PerformService performService() {
		return new DefaultPerformService();
	}
	
}
