package com.medica.core.domain.rule.components;

import com.medica.core.domain.DiagnosisCoreData;

public class NotContainer implements Expression {

	private Expression expression;
	
	@Override
	public Boolean evaluate(DiagnosisCoreData data) {
		return !expression.evaluate(data);
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

}
