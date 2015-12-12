package com.medica.core.domain.rule.components;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.medica.core.domain.CoreDataPiece;
import com.medica.core.domain.DiagnosisCoreData;

public class AndContainerTest {

	private static final String ATTRIBUTE_VALUE_1 = "ATTRIBUTE_VALUE_1";
	private static final String ATTRIBUTE_NAME_1 = "ATTRIBUTE_NAME_1";
	
	private static final String ATTRIBUTE_VALUE_2 = "ATTRIBUTE_VALUE_2";
	private static final String ATTRIBUTE_NAME_2 = "ATTRIBUTE_NAME_2";
	
	private static final String ATTRIBUTE_VALUE_3 = "ATTRIBUTE_VALUE_3";
	private static final String ATTRIBUTE_NAME_3 = "ATTRIBUTE_NAME_3";

	@Test
	public void shouldEvaluateContainerContainingOneElement() {
		AndContainer container = new AndContainer();
		container.addExpression(getEqualsExpression(ATTRIBUTE_NAME_1, ATTRIBUTE_VALUE_1));
		
		DiagnosisCoreData validData = getSinglePieceCoreData(ATTRIBUTE_NAME_1, ATTRIBUTE_VALUE_1);
		boolean result = container.evaluate(validData);
		
		assertTrue(result);
	}
	
	@Test
	public void shouldEvaluateContainerContainingManyElementsAllTrue() {
		AndContainer container = new AndContainer();
		container.addExpression(getEqualsExpression(ATTRIBUTE_NAME_1, ATTRIBUTE_VALUE_1));
		container.addExpression(getEqualsExpression(ATTRIBUTE_NAME_2, ATTRIBUTE_VALUE_2));
		container.addExpression(getEqualsExpression(ATTRIBUTE_NAME_3, ATTRIBUTE_VALUE_3));
		
		DiagnosisCoreData validData = getValidCoreData();
		boolean result = container.evaluate(validData);
		
		assertTrue(result);
	}
	
	@Test
	public void shouldEvaluateContainerContainingManyElementsAllFalse() {
		AndContainer container = new AndContainer();
		container.addExpression(getEqualsExpression(ATTRIBUTE_NAME_1, ATTRIBUTE_VALUE_1));
		container.addExpression(getEqualsExpression(ATTRIBUTE_NAME_2, ATTRIBUTE_VALUE_2));
		container.addExpression(getEqualsExpression(ATTRIBUTE_NAME_3, ATTRIBUTE_VALUE_3));
		
		DiagnosisCoreData invalidData = getInvalidCoreData();
		boolean result = container.evaluate(invalidData);
		
		assertFalse(result);
	}
	
	@Test
	public void shouldEvaluateContainerContainingManyElementsSomeFalse() {
		AndContainer container = new AndContainer();
		container.addExpression(getEqualsExpression(ATTRIBUTE_NAME_1, ATTRIBUTE_VALUE_1));
		container.addExpression(getEqualsExpression(ATTRIBUTE_NAME_2, ATTRIBUTE_VALUE_2));
		container.addExpression(getEqualsExpression(ATTRIBUTE_NAME_3, ATTRIBUTE_VALUE_3));
		
		DiagnosisCoreData invalidData = getPartialyInvalidCoreData();
		boolean result = container.evaluate(invalidData);
		
		assertFalse(result);
	}
	
	private DiagnosisCoreData getValidCoreData() { 
		DiagnosisCoreData data = new DiagnosisCoreData();
		CoreDataPiece dataPiece1 = new CoreDataPiece();
		CoreDataPiece dataPiece2 = new CoreDataPiece();
		CoreDataPiece dataPiece3 = new CoreDataPiece();
		
		dataPiece1.setName(ATTRIBUTE_NAME_1);
		dataPiece1.setValue(ATTRIBUTE_VALUE_1);
		data.addDataPiece(dataPiece1);
		
		dataPiece2.setName(ATTRIBUTE_NAME_2);
		dataPiece2.setValue(ATTRIBUTE_VALUE_2);
		data.addDataPiece(dataPiece2);
		
		dataPiece3.setName(ATTRIBUTE_NAME_3);
		dataPiece3.setValue(ATTRIBUTE_VALUE_3);
		data.addDataPiece(dataPiece3);
		
		return data;
	}
	
	private DiagnosisCoreData getInvalidCoreData() { 
		DiagnosisCoreData data = new DiagnosisCoreData();
		CoreDataPiece dataPiece1 = new CoreDataPiece();
		CoreDataPiece dataPiece2 = new CoreDataPiece();
		CoreDataPiece dataPiece3 = new CoreDataPiece();
		
		dataPiece1.setName(ATTRIBUTE_NAME_1);
		dataPiece1.setValue(ATTRIBUTE_VALUE_2);
		data.addDataPiece(dataPiece1);
		
		dataPiece2.setName(ATTRIBUTE_NAME_2);
		dataPiece2.setValue(ATTRIBUTE_VALUE_3);
		data.addDataPiece(dataPiece2);
		
		dataPiece3.setName(ATTRIBUTE_NAME_3);
		dataPiece3.setValue(ATTRIBUTE_VALUE_1);
		data.addDataPiece(dataPiece3);
		
		return data;
	}
	
	private DiagnosisCoreData getPartialyInvalidCoreData() { 
		DiagnosisCoreData data = new DiagnosisCoreData();
		CoreDataPiece dataPiece1 = new CoreDataPiece();
		CoreDataPiece dataPiece2 = new CoreDataPiece();
		CoreDataPiece dataPiece3 = new CoreDataPiece();
		
		dataPiece1.setName(ATTRIBUTE_NAME_1);
		dataPiece1.setValue(ATTRIBUTE_VALUE_1);
		data.addDataPiece(dataPiece1);
		
		dataPiece2.setName(ATTRIBUTE_NAME_2);
		dataPiece2.setValue(ATTRIBUTE_VALUE_3);
		data.addDataPiece(dataPiece2);
		
		dataPiece3.setName(ATTRIBUTE_NAME_3);
		dataPiece3.setValue(ATTRIBUTE_VALUE_3);
		data.addDataPiece(dataPiece3);
		
		return data;
	}
	
	private DiagnosisCoreData getSinglePieceCoreData(String name, String value) {
		CoreDataPiece dataPiece = new CoreDataPiece();
		DiagnosisCoreData data = new DiagnosisCoreData();
		
		dataPiece.setName(name);
		dataPiece.setValue(value);
		data.addDataPiece(dataPiece);
		
		return data;
	}
	
	private Expression getEqualsExpression(String name, String value) {
		SimpleExpression expression = new SimpleExpression();
		
		expression.setAttributeName(name);
		expression.setOperator(Operators.EQUAL);
		expression.setValue(value);
		
		return expression;
	}
}
