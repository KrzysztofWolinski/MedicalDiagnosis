package com.medica.integration.service.diagnosis;

import static org.junit.Assert.assertNotNull;

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
import com.medica.integration.config.BaseTestConfig;
import com.medica.integration.repository.DiagnosisDataRepository;
import com.medica.integration.repository.DiagnosisResultRepository;
import com.medica.integration.repository.DiagnosisRuleRepository;
import com.medica.integration.repository.UserRepository;
import com.medica.integration.service.diagnosis.domain.DiagnosisForm;
import com.medica.integration.service.diagnosis.impl.DiagnosisServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DiagnosisServiceImplTest.Config.class })
public class DiagnosisServiceImplTest {

	@Inject
	DiagnosisService diagnosisService;
		
	@Test
	public void shouldMapFormTemplateToObject() {
		DiagnosisForm form = this.diagnosisService.getForm();
		
		assertNotNull(form);
	}
	
	@Configuration
	@Import({BaseTestConfig.class})
	static class Config {
		
		@Bean
		public DiagnosisService diagnosisService() {
			return new DiagnosisServiceImpl();
		}
		
		@Bean
		public DiagnosisDataRepository diagnosisDataRepository() {
			return Mockito.mock(DiagnosisDataRepository.class);
		}
		
		@Bean
		public UserRepository userRepository() {
			return Mockito.mock(UserRepository.class);
		}
		
		@Bean
		public DiagnosisRuleRepository diagnosisRuleRepository() {
			return Mockito.mock(DiagnosisRuleRepository.class);
		}
		
		@Bean
		public DiagnosisResultRepository diagnosisResultRepository() {
			return Mockito.mock(DiagnosisResultRepository.class);
		}
		
		@Bean
		public DiagnosisCoreService diagnosisCoreService() {
			return Mockito.mock(DiagnosisCoreService.class);
		}
		
	}
}
