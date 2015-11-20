package com.medica.integration.service.diagnosis.utils;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.medica.integration.service.diagnosis.domain.ChoiceFieldValue;
import com.medica.integration.service.diagnosis.domain.DiagnosisForm;
import com.medica.integration.service.diagnosis.domain.FieldGroup;
import com.medica.integration.service.diagnosis.domain.FieldValueType;
import com.medica.integration.service.diagnosis.domain.SimpleFieldValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DiagnosisFormGenerator.Config.class })
public class DiagnosisFormGenerator {

	@Inject
	ObjectMapper mapper;
	
	@Test
	public void generateFormFromObject() throws JsonGenerationException, JsonMappingException, IOException {
		File file = new File("/home/wagh/form.json");
		
		DiagnosisForm form = new DiagnosisForm();
		
		FieldGroup fieldGroup1 = new FieldGroup();
		FieldGroup fieldGroup2 = new FieldGroup();
		
		SimpleFieldValue simpleFieldValue1 = new SimpleFieldValue();
		SimpleFieldValue simpleFieldValue2 = new SimpleFieldValue();
		SimpleFieldValue simpleFieldValue3 = new SimpleFieldValue();
		ChoiceFieldValue choiceFieldValue1 = new ChoiceFieldValue();

		simpleFieldValue1.setType(FieldValueType.STRING);
		simpleFieldValue2.setType(FieldValueType.INTEGER);
		simpleFieldValue2.setType(FieldValueType.DATE);
		
		choiceFieldValue1.setType(FieldValueType.STRING);
		choiceFieldValue1.addPossibleValue("possibleValue1");
		choiceFieldValue1.addPossibleValue("possibleValue2");
		
		fieldGroup1.addField(simpleFieldValue1);
		fieldGroup1.addField(simpleFieldValue2);
		
		fieldGroup2.addField(simpleFieldValue3);
		fieldGroup2.addField(choiceFieldValue1);
		
		form.addFormFieldGroup(fieldGroup1);
		form.addFormFieldGroup(fieldGroup2);
		
		mapper.writeValue(file, form);
		
	}
	
	@Configuration
	static class Config {
				
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

			return mapper;
		}
		
	}
}
