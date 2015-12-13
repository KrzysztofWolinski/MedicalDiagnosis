package com.medica.core.domain.rule;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.medica.core.domain.CoreDataPiece;
import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.rule.components.AndContainer;
import com.medica.core.domain.rule.components.Operators;
import com.medica.core.domain.rule.components.SimpleExpression;

public class DiagnosisCoreRuleTest {

	private static final String SAMPLE_ATTRIBUTE_1 = "SAMPLE_ATTRIBUTE_1";
	private static final String SAMPLE_VLAUE_1 = "SAMPLE_VLAUE_1";
	private static final String SAMPLE_DECISION_1 = "SAMPLE_DECISION_1";
	
	private static final String SAMPLE_ATTRIBUTE_2 = "SAMPLE_ATTRIBUTE_2";
	private static final String SAMPLE_VLAUE_2 = "SAMPLE_VLAUE_2";
	private static final String SAMPLE_DECISION_2 = "SAMPLE_DECISION_2";

	@Test
	public void shouldCreateNewEmptyRule() {
		DiagnosisCoreRule rule = new DiagnosisCoreRule();

		boolean result = rule.evaluate(getEmptyCoreData());
		
		assertFalse(result);
	}
	
	@Test
	public void shouldEvaluateSingleExpressionRule() {
		DiagnosisCoreRule rule = getSampleSimpleExpressionRule();
		
		boolean validResult = rule.evaluate(getSample1CoreData());
		boolean invalidResult = rule.evaluate(getEmptyCoreData());
		
		assertTrue(validResult);
		assertFalse(invalidResult);
	}
	
	@Test
	public void shouldEvaluateAndContainerExpression() {
		DiagnosisCoreRule rule = getSampleAndContainerExpressionRule();
		
		DiagnosisCoreData validData = new DiagnosisCoreData();
		validData.addDataPiece(getSampleCoreDataPiece(SAMPLE_ATTRIBUTE_1, SAMPLE_VLAUE_1));
		validData.addDataPiece(getSampleCoreDataPiece(SAMPLE_ATTRIBUTE_2, SAMPLE_VLAUE_2));
		
		DiagnosisCoreData invalidData = new DiagnosisCoreData();
		invalidData.addDataPiece(getSampleCoreDataPiece(SAMPLE_ATTRIBUTE_1, SAMPLE_VLAUE_2));
		invalidData.addDataPiece(getSampleCoreDataPiece(SAMPLE_ATTRIBUTE_2, SAMPLE_VLAUE_1));
		
		boolean validResult = rule.evaluate(validData);
		boolean invalidResult = rule.evaluate(invalidData);
		
		assertTrue(validResult);
		assertFalse(invalidResult);
	}
	
	@Test
	public void shouldEvaluateOrContainerExpression() {
		DiagnosisCoreRule rule = getSampleOrContainerExpressionRule();
		
		DiagnosisCoreData validData = new DiagnosisCoreData();
		validData.addDataPiece(getSampleCoreDataPiece(SAMPLE_ATTRIBUTE_1, SAMPLE_VLAUE_1));
		validData.addDataPiece(getSampleCoreDataPiece(SAMPLE_ATTRIBUTE_2, SAMPLE_VLAUE_2));
		
		DiagnosisCoreData validData2 = new DiagnosisCoreData();
		validData2.addDataPiece(getSampleCoreDataPiece(SAMPLE_ATTRIBUTE_1, SAMPLE_VLAUE_1));
		validData2.addDataPiece(getSampleCoreDataPiece(SAMPLE_ATTRIBUTE_2, SAMPLE_VLAUE_1));
		
		DiagnosisCoreData invalidData = new DiagnosisCoreData();
		invalidData.addDataPiece(getSampleCoreDataPiece(SAMPLE_ATTRIBUTE_1, SAMPLE_VLAUE_2));
		invalidData.addDataPiece(getSampleCoreDataPiece(SAMPLE_ATTRIBUTE_2, SAMPLE_VLAUE_1));
		
		boolean validResult = rule.evaluate(validData);
		boolean validResult2 = rule.evaluate(validData2);
		boolean invalidResult = rule.evaluate(invalidData);
		
		assertTrue(validResult);
		assertTrue(validResult2);
		assertFalse(invalidResult);
	}
	
	private DiagnosisCoreRule getSampleSimpleExpressionRule() {
		SimpleExpression expression = new SimpleExpression();
		expression.setAttributeName(SAMPLE_ATTRIBUTE_1);
		expression.setOperator(Operators.EQUAL);
		expression.setValue(SAMPLE_VLAUE_1);
		
		DiagnosisCoreRule rule = new DiagnosisCoreRule();
		rule.setDecision(SAMPLE_DECISION_1);
		rule.setExpression(expression);
		
		return rule;
	}
	
	private DiagnosisCoreRule getSampleOrContainerExpressionRule() {
		SimpleExpression expression = new SimpleExpression();
		expression.setAttributeName(SAMPLE_ATTRIBUTE_1);
		expression.setOperator(Operators.EQUAL);
		expression.setValue(SAMPLE_VLAUE_1);
		
		DiagnosisCoreRule rule = new DiagnosisCoreRule();
		rule.setDecision(SAMPLE_DECISION_1);
		rule.setExpression(expression);
		
		return rule;
	}
	
	private DiagnosisCoreRule getSampleAndContainerExpressionRule() {
		SimpleExpression expression1 = new SimpleExpression();
		expression1.setAttributeName(SAMPLE_ATTRIBUTE_1);
		expression1.setOperator(Operators.EQUAL);
		expression1.setValue(SAMPLE_VLAUE_1);
		
		SimpleExpression expression2 = new SimpleExpression();
		expression2.setAttributeName(SAMPLE_ATTRIBUTE_2);
		expression2.setOperator(Operators.EQUAL);
		expression2.setValue(SAMPLE_VLAUE_2);
		
		AndContainer mainExpression = new AndContainer();
		mainExpression.addExpression(expression1);
		mainExpression.addExpression(expression2);
		
		DiagnosisCoreRule rule = new DiagnosisCoreRule();
		rule.setDecision(SAMPLE_DECISION_1);
		rule.setExpression(mainExpression);
		
		return rule;
	}
	
	private DiagnosisCoreData getEmptyCoreData() {
		return new DiagnosisCoreData();
	}
	
	private DiagnosisCoreData getSample1CoreData() {
		DiagnosisCoreData data = new DiagnosisCoreData();
		CoreDataPiece dataPiece = new CoreDataPiece();
		
		dataPiece.setName(SAMPLE_ATTRIBUTE_1);
		dataPiece.setValue(SAMPLE_VLAUE_1);
		
		data.addDataPiece(dataPiece);
		
		return data;
	}
	
	private CoreDataPiece getSampleCoreDataPiece(String name, String value) {
		CoreDataPiece dataPiece = new CoreDataPiece();
		
		dataPiece.setName(name);
		dataPiece.setValue(value);
		
		return dataPiece;
	}
	
}
