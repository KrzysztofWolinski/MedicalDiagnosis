package com.medica.integration.service.diagnosis;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.medica.integration.repository.DiagnosisDataRepository;
import com.medica.integration.repository.UserRepository;
import com.medica.integration.service.diagnosis.domain.ChoiceFieldValue;
import com.medica.integration.service.diagnosis.domain.DiagnosisForm;
import com.medica.integration.service.diagnosis.domain.SimpleFieldValue;
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
	@PropertySources({ @PropertySource("classpath:properties/medical-diagnosis-test.properties") })
	static class Config {
		
		@Bean
		public DiagnosisService diagnosisService() {
			return new DiagnosisServiceImpl();
		}
		
		@Bean
		public DiagnosisDataRepository diagnosisRepository() {
			return Mockito.mock(DiagnosisDataRepository.class);
		}
		
		@Bean
		public UserRepository userRepository() {
			return Mockito.mock(UserRepository.class);
		}
		
		@Bean
		public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
			return new PropertySourcesPlaceholderConfigurer();
		}
		
		@Bean
		@Primary
		public ObjectMapper objectMapper() {
			final ObjectMapper mapper = new ObjectMapper();

			mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			mapper.configure(Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
			mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
			mapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
			mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			mapper.registerSubtypes(
				new NamedType(SimpleFieldValue.class, "SimpleFieldValue"),
				new NamedType(ChoiceFieldValue.class, "ChoiceFieldValue"));

			return mapper;
		}
		
	}
}
