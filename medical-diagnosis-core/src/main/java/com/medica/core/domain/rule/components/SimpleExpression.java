package com.medica.core.domain.rule.components;

import com.medica.core.domain.CoreDataPiece;
import com.medica.core.domain.DiagnosisCoreData;

public class SimpleExpression implements Expression {

	private String attributeName;
	
	private Operators operator;
	
	private String value;

	@Override
	public Boolean evaluate(DiagnosisCoreData data) {
		CoreDataPiece dataPiece= data.getDataPiece(attributeName);
		
		if (dataPiece == null) {
			return false;
		} else {
			return evaluateExpression(dataPiece);
		}
	}
	
	private Boolean evaluateExpression(CoreDataPiece dataPiece) {
		try {
			switch (operator) {
				case EQUAL :
					return value.equals(dataPiece.getValue());
				case GREATER_THAN:
					return Integer.parseInt(dataPiece.getValue()) > Integer.parseInt(value);
				case SMALLER_THAN:
					return Integer.parseInt(dataPiece.getValue()) < Integer.parseInt(value);
				default:
					return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public Operators getOperator() {
		return operator;
	}

	public void setOperator(Operators operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
