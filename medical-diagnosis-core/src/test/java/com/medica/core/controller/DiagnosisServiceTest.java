package com.medica.core.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.medica.core.controller.impl.DefaultDiagnosisCoreService;
import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.DiagnosisCoreResult;
import com.medica.core.domain.DiagnosisCoreRule;
import com.medica.core.domain.communication.analyse.AnalyseRequest;
import com.medica.core.domain.communication.learn.LearnRequest;
import com.medica.core.domain.communication.perform.PerformRequest;
import com.medica.core.service.analyse.AnalyseService;
import com.medica.core.service.analyse.impl.DefaultAnalyseService;
import com.medica.core.service.learn.LearnService;
import com.medica.core.service.learn.impl.DefaultLearnService;
import com.medica.core.service.perform.PerformService;
import com.medica.core.service.perform.impl.DefaultPerformService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DiagnosisServiceTest.Config.class })
public class DiagnosisServiceTest {

	@Inject
	private DiagnosisCoreService diagnosisService;
	
	@Inject
	private AnalyseService analyseService;
	
	@Inject
	private PerformService performService;
	
	@Inject
	private LearnService learnService;
	
	@Test
	public void shouldInitializeContext() {
		
	}
	
	@Test
	public void shouldCallAnalyseService() {
		diagnosisService.analyse(new AnalyseRequest());
		
		verify(analyseService, times(1)).analyseData(Mockito.anyListOf(DiagnosisCoreData.class));
	}
	
	@Test
	public void shouldCallLearnService() {
		diagnosisService.learn(new LearnRequest());
		
		verify(learnService, times(1)).generateRules(Mockito.anyListOf(DiagnosisCoreResult.class));
	}
	
	@Test
	public void shouldCallPerformService() {
		diagnosisService.perform(new PerformRequest());
		
		verify(performService, times(1)).performDiagnosis(Mockito.any(DiagnosisCoreData.class), Mockito.anyListOf(DiagnosisCoreRule.class));
	}
	
	@Configuration
	static class Config {
	
		@Bean
		public DiagnosisCoreService diagnosisService() {
			return new DefaultDiagnosisCoreService();
		}
		
		@Bean
		public LearnService learnService() {
			return Mockito.mock(DefaultLearnService.class);
		}
		
		@Bean
		public PerformService performService() {
			return Mockito.mock(DefaultPerformService.class);
		}
		
		@Bean
		public AnalyseService analyseService() {
			return Mockito.mock(DefaultAnalyseService.class);
		}
		
	}
}
