package com.medica.integration.service.converters;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.medica.core.domain.rule.DiagnosisCoreRule;
import com.medica.core.domain.rule.components.AndContainer;
import com.medica.core.domain.rule.components.Expression;
import com.medica.core.domain.rule.components.Operators;
import com.medica.core.domain.rule.components.OrContainer;
import com.medica.core.domain.rule.components.SimpleExpression;
import com.medica.integration.domain.diagnosis.DiagnosisRule;

public class DiagnosisRuleConverterTest {

	private static final String SIMPLE_ATTRIBUTE_NAME_1 = "SIMPLE_NAME_1";
	private static final String SIMPLE_ATTRIBUTE_VALUE_1 = "SIMPLE_VALUE_1";
	
	private static final String SIMPLE_ATTRIBUTE_NAME_2 = "SIMPLE_NAME_2";
	private static final String SIMPLE_ATTRIBUTE_VALUE_2 = "SIMPLE_VALUE_2";
	
	private static final String SIMPLE_ATTRIBUTE_NAME_3 = "SIMPLE_NAME_3";
	private static final String SIMPLE_ATTRIBUTE_VALUE_3 = "SIMPLE_VALUE_3";
	
	private static final int SAMPLE_PROBABILITY = 90;
	private static final String SAMPLE_DECISION = "SAMPLE_DECISION";

	@Test
	public void shouldConvertSimpleExpressionRule() {
		DiagnosisCoreRule generatedRule = getSimpleExpressionRule();
		DiagnosisRule convertedRule = DiagnosisRuleConverter.convertToDao(generatedRule);
		DiagnosisCoreRule retrievedRule = DiagnosisRuleConverter.convertToDto(convertedRule);
		
		SimpleExpression sourceExpression = (SimpleExpression) generatedRule.getExpression();
		SimpleExpression retrivedExpression = (SimpleExpression) retrievedRule.getExpression();
		
		assertThat(generatedRule.getDecision(), equalTo(retrievedRule.getDecision()));
		assertThat(generatedRule.getProbability(), equalTo(retrievedRule.getProbability()));
		
		compareExpressions(sourceExpression, retrivedExpression);
	}
	
	@Test
	public void shouldConvertComplexRule() {
		DiagnosisCoreRule generatedRule = getComplexRule();
		DiagnosisRule convertedRule = DiagnosisRuleConverter.convertToDao(generatedRule);
		DiagnosisCoreRule retrievedRule = DiagnosisRuleConverter.convertToDto(convertedRule);
		
		OrContainer sourceExpression = (OrContainer) generatedRule.getExpression();
		OrContainer retrivedExpression = (OrContainer) retrievedRule.getExpression();
		
		assertThat(generatedRule.getDecision(), equalTo(retrievedRule.getDecision()));
		assertThat(generatedRule.getProbability(), equalTo(retrievedRule.getProbability()));
		
		compareExpressions(sourceExpression, retrivedExpression);
	}
	
	private void compareExpressions(Expression expected, Expression given) {
		assertThat(given, is(instanceOf(expected.getClass())));
		
		if (expected instanceof OrContainer) {
			List<Expression> expectedExpressions = ((OrContainer) expected).getExpressions();
			List<Expression> givenExpressions = ((OrContainer) given).getExpressions();
			
			assertThat(expectedExpressions.size(), is(equalTo(givenExpressions.size())));
			
			for (int i = 0; i < expectedExpressions.size(); i++) {
				compareExpressions(expectedExpressions.get(i), givenExpressions.get(i));
			}
		} else if (expected instanceof AndContainer) {
			List<Expression> expectedExpressions = ((AndContainer) expected).getExpressions();
			List<Expression> givenExpressions = ((AndContainer) given).getExpressions();
			
			assertThat(expectedExpressions.size(), is(equalTo(givenExpressions.size())));
			
			for (int i = 0; i < expectedExpressions.size(); i++) {
				compareExpressions(expectedExpressions.get(i), givenExpressions.get(i));
			}
		} else if (expected instanceof SimpleExpression) {
			assertThat(((SimpleExpression) expected).getAttributeName(), equalTo(((SimpleExpression) given).getAttributeName()));
			assertThat(((SimpleExpression) expected).getOperator(), equalTo(((SimpleExpression) given).getOperator()));
			assertThat(((SimpleExpression) expected).getValue(), equalTo(((SimpleExpression) given).getValue()));
		}
	}
	
	private DiagnosisCoreRule getSimpleExpressionRule() {
		DiagnosisCoreRule simpleRule = new DiagnosisCoreRule();
		SimpleExpression expression = new SimpleExpression();
		
		expression.setAttributeName(SIMPLE_ATTRIBUTE_NAME_1);
		expression.setOperator(Operators.EQUAL);
		expression.setValue(SIMPLE_ATTRIBUTE_VALUE_1);
		
		simpleRule.setExpression(expression);
		simpleRule.setDecision(SAMPLE_DECISION);
		simpleRule.setProbability(SAMPLE_PROBABILITY);
		
		
		return simpleRule;
	}
	
	private DiagnosisCoreRule getComplexRule() {
		DiagnosisCoreRule complexRule = new DiagnosisCoreRule();

		SimpleExpression simpleExpression1 = new SimpleExpression();
		simpleExpression1.setAttributeName(SIMPLE_ATTRIBUTE_NAME_1);
		simpleExpression1.setOperator(Operators.EQUAL);
		simpleExpression1.setValue(SIMPLE_ATTRIBUTE_VALUE_1);
		
		SimpleExpression simpleExpression2 = new SimpleExpression();
		simpleExpression2.setAttributeName(SIMPLE_ATTRIBUTE_NAME_2);
		simpleExpression2.setOperator(Operators.EQUAL);
		simpleExpression2.setValue(SIMPLE_ATTRIBUTE_VALUE_2);
		
		SimpleExpression simpleExpression3 = new SimpleExpression();
		simpleExpression3.setAttributeName(SIMPLE_ATTRIBUTE_NAME_3);
		simpleExpression3.setOperator(Operators.EQUAL);
		simpleExpression3.setValue(SIMPLE_ATTRIBUTE_VALUE_3);
		
		AndContainer andContainer = new AndContainer();
		OrContainer orContainer = new OrContainer();
		
		andContainer.addExpression(simpleExpression1);
		andContainer.addExpression(simpleExpression2);
		orContainer.addExpression(simpleExpression3);
		orContainer.addExpression(andContainer);
		
		complexRule.setExpression(orContainer);
		complexRule.setDecision(SAMPLE_DECISION);
		complexRule.setProbability(SAMPLE_PROBABILITY);
		
		
		return complexRule;
	}
}
