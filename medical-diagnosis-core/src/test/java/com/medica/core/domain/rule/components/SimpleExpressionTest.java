package com.medica.core.domain.rule.components;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.medica.core.domain.CoreDataPiece;
import com.medica.core.domain.DiagnosisCoreData;

public class SimpleExpressionTest {

	private static final String STRING_NAME = "ATTRIBUTE_NAME_1";
	private static final String STRING_VALUE = "ATTRIBUTE_VALUE_1";
	
	private static final String INT_NAME = "ATTRIBUTE_NAME_2";
	private static final String INT_VALUE = "10";
	private static final String INT_VALUE_GREATER = "12";
	private static final String INT_VALUE_SMALLER = "1";
	private static final String INT_INVALID_VALUE = "INVALID INTEGER";
	
	@Test
	public void shouldEvaluateSimpleEqualsExpression() {
		SimpleExpression expression = getEqualsExpression(STRING_NAME, Operators.EQUAL, STRING_VALUE);
		DiagnosisCoreData inputData = getCoreData(STRING_NAME, STRING_VALUE);
		
		boolean result = expression.evaluate(inputData);
		
		assertTrue(result);
	}
	
	@Test
	public void shouldEvaluateSimpleValidGreaterThanExpression() {
		SimpleExpression expression = getEqualsExpression(INT_NAME, Operators.GREATER_THAN, INT_VALUE);
		DiagnosisCoreData validInputData = getCoreData(INT_NAME, INT_VALUE_GREATER);
		DiagnosisCoreData invalidInputData = getCoreData(INT_NAME, INT_VALUE_SMALLER);
		
		boolean validResult = expression.evaluate(validInputData);
		boolean invalidResult = expression.evaluate(invalidInputData);
		
		assertTrue(validResult);
		assertFalse(invalidResult);
	}

	@Test
	public void shouldEvaluateSimpleValidSmallerThanExpression() {
		SimpleExpression expression = getEqualsExpression(INT_NAME, Operators.SMALLER_THAN, INT_VALUE);
		DiagnosisCoreData validInputData = getCoreData(INT_NAME, INT_VALUE_SMALLER);
		DiagnosisCoreData invalidInputData = getCoreData(INT_NAME, INT_VALUE_GREATER);
		
		boolean validResult = expression.evaluate(validInputData);
		boolean invalidResult = expression.evaluate(invalidInputData);
		
		assertTrue(validResult);
		assertFalse(invalidResult);
	}
	
	@Test
	public void shouldHandleSimpleInvalidSmallerThanExpression() {
		SimpleExpression expression = getEqualsExpression(INT_NAME, Operators.SMALLER_THAN, INT_VALUE);
		DiagnosisCoreData invalidInputData = getCoreData(INT_NAME, INT_INVALID_VALUE);
		
		boolean invalidResult = expression.evaluate(invalidInputData);
		
		assertFalse(invalidResult);
	}
	
	private SimpleExpression getEqualsExpression(String name, Operators operator, String value) {
		SimpleExpression expression = new SimpleExpression();
		
		expression.setAttributeName(name);
		expression.setOperator(operator);
		expression.setValue(value);
		
		return expression;
	}
	
	private DiagnosisCoreData getCoreData(String name, String value) { 
		DiagnosisCoreData data = new DiagnosisCoreData();
		CoreDataPiece dataPiece1 = new CoreDataPiece();
		
		dataPiece1.setName(name);
		dataPiece1.setValue(value);
		data.addDataPiece(dataPiece1);
		
		return data;
	}
}
