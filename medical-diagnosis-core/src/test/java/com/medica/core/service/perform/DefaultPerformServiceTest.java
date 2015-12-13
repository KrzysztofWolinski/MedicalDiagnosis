package com.medica.core.service.perform;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.medica.core.domain.CoreDataPiece;
import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.DiagnosisCoreResult;
import com.medica.core.domain.rule.DiagnosisCoreRule;
import com.medica.core.domain.rule.components.AndContainer;
import com.medica.core.domain.rule.components.Operators;
import com.medica.core.domain.rule.components.OrContainer;
import com.medica.core.domain.rule.components.SimpleExpression;
import com.medica.core.service.perform.impl.DefaultPerformService;

public class DefaultPerformServiceTest {

	private static final String ATTRIBUTE_1_VALUE = "ATTRIBUTE_VALUE_1";
	private static final String ATTRIBUTE_1_NAME = "ATTRIBUTE_NAME_1";
	
	private static final String ATTRIBUTE_2_VALUE = "ATTRIBUTE_VALUE_2";
	private static final String ATTRIBUTE_2_NAME = "ATTRIBUTE_NAME_2";
	
	private static final String ATTRIBUTE_3_VALUE = "ATTRIBUTE_VALUE_3";
	private static final String ATTRIBUTE_3_NAME = "ATTRIBUTE_NAME_3";

	private static final int CONDITION_2_PROBABILITY = 90;
	private static final String CONDITION_2_NAME = "CONDITION_2_NAME";

	private static final int CONDITION_1_PROBABILITY = 100;
	private static final String CONDITION_1_NAME = "CONDITION_1_NAME";
	
	DefaultPerformService performService = new DefaultPerformService();
	
	@Test
	public void shouldWorkForSimpleExample() {
		List<DiagnosisCoreRule> rules = prepareSimpleRules();
		DiagnosisCoreData data = prepareSimpleData();
		
		DiagnosisCoreResult diagnosisResult = performService.performDiagnosis(data, rules);
		
		assertThat(diagnosisResult.getConditionProbability().size(), equalTo(1));
		assertThat(diagnosisResult.getConditionProbability().get(CONDITION_1_NAME), equalTo(CONDITION_1_PROBABILITY));
		
	}
	
	@Test
	public void shouldWorkForComplexExample() {
		List<DiagnosisCoreRule> rules = prepareComplexRules();

		DiagnosisCoreData validData1 = new DiagnosisCoreData();
		validData1.addDataPiece(getSampleCoreDataPiece(ATTRIBUTE_1_NAME, ATTRIBUTE_1_VALUE));
		validData1.addDataPiece(getSampleCoreDataPiece(ATTRIBUTE_2_NAME, ATTRIBUTE_2_VALUE));
		
		DiagnosisCoreData validData2 = new DiagnosisCoreData();
		validData2.addDataPiece(getSampleCoreDataPiece(ATTRIBUTE_1_NAME, ATTRIBUTE_1_VALUE));
		validData2.addDataPiece(getSampleCoreDataPiece(ATTRIBUTE_2_NAME, ATTRIBUTE_2_VALUE));
		validData2.addDataPiece(getSampleCoreDataPiece(ATTRIBUTE_3_NAME, ATTRIBUTE_1_VALUE));
		
		DiagnosisCoreData invalidData = new DiagnosisCoreData();
		invalidData.addDataPiece(getSampleCoreDataPiece(ATTRIBUTE_1_NAME, ATTRIBUTE_2_VALUE));
		invalidData.addDataPiece(getSampleCoreDataPiece(ATTRIBUTE_2_NAME, ATTRIBUTE_2_VALUE));
		invalidData.addDataPiece(getSampleCoreDataPiece(ATTRIBUTE_3_NAME, ATTRIBUTE_3_VALUE));
		
		DiagnosisCoreResult validDiagnosisResult1 = performService.performDiagnosis(validData1, rules);
		DiagnosisCoreResult validDiagnosisResult2 = performService.performDiagnosis(validData2, rules);
		DiagnosisCoreResult invalidDiagnosisResult = performService.performDiagnosis(invalidData, rules);
		
		assertThat(validDiagnosisResult1.getConditionProbability().size(), equalTo(2));
		assertThat(validDiagnosisResult1.getConditionProbability().get(CONDITION_1_NAME), equalTo(CONDITION_1_PROBABILITY));
		assertThat(validDiagnosisResult1.getConditionProbability().get(CONDITION_2_NAME), equalTo(CONDITION_2_PROBABILITY));
		
		assertThat(validDiagnosisResult2.getConditionProbability().size(), equalTo(2));
		assertThat(validDiagnosisResult2.getConditionProbability().get(CONDITION_1_NAME), equalTo(CONDITION_1_PROBABILITY));
		assertThat(validDiagnosisResult2.getConditionProbability().get(CONDITION_2_NAME), equalTo(CONDITION_2_PROBABILITY));
		
		assertThat(invalidDiagnosisResult.getConditionProbability().size(), equalTo(1));
		assertThat(invalidDiagnosisResult.getConditionProbability().get(CONDITION_2_NAME), equalTo(CONDITION_2_PROBABILITY));
	}
	
	private List<DiagnosisCoreRule> prepareSimpleRules() {
		List<DiagnosisCoreRule> rules = new ArrayList<DiagnosisCoreRule>();
		DiagnosisCoreRule rule1 = new DiagnosisCoreRule();
		
		rule1.setExpression(getSimpleExpression(ATTRIBUTE_1_NAME, Operators.EQUAL, ATTRIBUTE_1_VALUE));
		rule1.setDecision(CONDITION_1_NAME);
		rule1.setProbability(CONDITION_1_PROBABILITY);
		
		rules.add(rule1);
		
		return rules;
	}
	
	private DiagnosisCoreData prepareSimpleData() {
		DiagnosisCoreData data = new DiagnosisCoreData();
		CoreDataPiece dataPiece1 = new CoreDataPiece();
		
		dataPiece1.setName(ATTRIBUTE_1_NAME);
		dataPiece1.setValue(ATTRIBUTE_1_VALUE);

		data.addDataPiece(dataPiece1);
		
		return data;
	}
	
	private List<DiagnosisCoreRule> prepareComplexRules() {
		List<DiagnosisCoreRule> rules = new ArrayList<DiagnosisCoreRule>();
		DiagnosisCoreRule rule1 = new DiagnosisCoreRule();
		
		AndContainer mainExpression = new AndContainer(); 
		mainExpression.addExpression(getSimpleExpression(ATTRIBUTE_1_NAME, Operators.EQUAL, ATTRIBUTE_1_VALUE));
		
		OrContainer internalExpression = new OrContainer();
		internalExpression.addExpression(getSimpleExpression(ATTRIBUTE_2_NAME, Operators.EQUAL, ATTRIBUTE_2_VALUE));
		internalExpression.addExpression(getSimpleExpression(ATTRIBUTE_3_NAME, Operators.EQUAL, ATTRIBUTE_3_VALUE));
		
		rule1.setExpression(mainExpression);
		rule1.setDecision(CONDITION_1_NAME);
		rule1.setProbability(CONDITION_1_PROBABILITY);
		
		rules.add(rule1);

		DiagnosisCoreRule rule2 = new DiagnosisCoreRule();
		rule2.setExpression(getSimpleExpression(ATTRIBUTE_2_NAME, Operators.EQUAL, ATTRIBUTE_2_VALUE));
		rule2.setDecision(CONDITION_2_NAME);
		rule2.setProbability(CONDITION_2_PROBABILITY);
		
		rules.add(rule2);
		
		return rules;
	}
	
	private DiagnosisCoreData prepareComplexData() {
		DiagnosisCoreData data = new DiagnosisCoreData();
		CoreDataPiece dataPiece1 = new CoreDataPiece();
		
		dataPiece1.setName(ATTRIBUTE_1_NAME);
		dataPiece1.setValue(ATTRIBUTE_1_VALUE);

		data.addDataPiece(dataPiece1);
		
		return data;
	}
	
	private SimpleExpression getSimpleExpression(String name, Operators operator, String value) {
		SimpleExpression expression = new SimpleExpression();
		
		expression.setAttributeName(name);
		expression.setOperator(operator);
		expression.setValue(value);
		
		return expression;
	}
	
	private CoreDataPiece getSampleCoreDataPiece(String name, String value) {
		CoreDataPiece dataPiece = new CoreDataPiece();
		
		dataPiece.setName(name);
		dataPiece.setValue(value);
		
		return dataPiece;
	}
	
}
