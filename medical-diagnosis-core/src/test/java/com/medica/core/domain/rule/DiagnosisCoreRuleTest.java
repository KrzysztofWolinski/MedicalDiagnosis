package com.medica.core.domain.rule;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.medica.core.domain.CoreDataPiece;
import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.rule.components.Operators;
import com.medica.core.domain.rule.components.SimpleExpression;

public class DiagnosisCoreRuleTest {

	private static final String SAMPLE_ATTRIBUTE_1 = "SAMPLE_ATTRIBUTE_1";
	
	private static final String SAMPLE_VLAUE_1 = "SAMPLE_VLAUE_1";
	
	private static final String SAMPLE_DECISION_1 = "SAMPLE_DECISION_1";

	@Test
	public void shouldCreateNewEmptyRule() {
		DiagnosisCoreRule rule = new DiagnosisCoreRule();

		boolean result = rule.evaluate(getEmptyCoreData());
		
		assertFalse(result);
	}
	
	@Test
	public void shouldEvaluateSingleExpressionRuleWithEmptyData() {
		DiagnosisCoreRule rule = getSampleSimpleExpressionRule();
		
		boolean invalidResult = rule.evaluate(getEmptyCoreData());
		
		assertFalse(invalidResult);
	}
	
	@Test
	public void shouldEvaluateSingleExpressionRuleWithProperData() {
		DiagnosisCoreRule rule = getSampleSimpleExpressionRule();
		
		boolean validResult = rule.evaluate(getSample1CoreData());
		
		assertTrue(validResult);
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
	
}
