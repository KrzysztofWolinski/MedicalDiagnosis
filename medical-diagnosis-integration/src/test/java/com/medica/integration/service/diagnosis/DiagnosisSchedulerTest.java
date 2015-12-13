package com.medica.integration.service.diagnosis;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.medica.core.controller.DiagnosisCoreService;
import com.medica.core.domain.communication.learn.LearnRequest;
import com.medica.core.domain.communication.learn.LearnResponse;
import com.medica.integration.config.BaseTestConfig;
import com.medica.integration.domain.diagnosis.DiagnosisData;
import com.medica.integration.domain.diagnosis.DiagnosisRule;
import com.medica.integration.repository.DiagnosisDataRepository;
import com.medica.integration.repository.DiagnosisResultRepository;
import com.medica.integration.repository.DiagnosisRuleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DiagnosisSchedulerTest.Config.class })
public class DiagnosisSchedulerTest {

	@Inject
	private DiagnosisDataRepository diagnosisDataRepository;
	
	@Inject
	private DiagnosisRuleRepository diagnosisRuleRepository;
	
	@Inject
	private DiagnosisCoreService diagnosisCoreService;

	@Inject
	private DiagnosisScheduler diagnosisScheduler;
	
	@Test
	public void shouldInitializeContext() {
		when(diagnosisDataRepository.findRatedData()).thenReturn(Arrays.asList(new DiagnosisData()));
		when(diagnosisCoreService.learn(Mockito.any(LearnRequest.class))).thenReturn(new LearnResponse());
		
		diagnosisScheduler.runLearnService();
		
		verify(diagnosisDataRepository, times(1)).findRatedData();
		verify(diagnosisCoreService, times(1)).learn(Mockito.any(LearnRequest.class));
		verify(diagnosisRuleRepository, times(1)).save(Mockito.anyListOf(DiagnosisRule.class));
	}
	
	@Configuration
	@Import({BaseTestConfig.class})
	static class Config {
		
		@Bean
		public DiagnosisRuleRepository diagnosisRuleRepository() {
			return Mockito.mock(DiagnosisRuleRepository.class);
		}
		
		@Bean
		public DiagnosisResultRepository diagnosisResultRepository() {
			return Mockito.mock(DiagnosisResultRepository.class);
		}
		
		@Bean
		public DiagnosisDataRepository diagnosisDataRepository() {
			return Mockito.mock(DiagnosisDataRepository.class);
		}
		
		@Bean
		public DiagnosisCoreService diagnosisCoreService() {
			return Mockito.mock(DiagnosisCoreService.class);
		}
		
		@Bean
		public DiagnosisScheduler diagnosisScheduler() {
			return new DiagnosisScheduler();
		}
	}
	
}
