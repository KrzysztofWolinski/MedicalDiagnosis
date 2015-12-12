package com.medica.core.domain.rule;

import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.rule.components.Expression;

public class DiagnosisCoreRule {

	private Expression expression;
	
	private String decision;

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}
	
	public boolean evaluate(DiagnosisCoreData input) {
		if (this.expression != null) {
			return this.expression.evaluate(input);
		} else {
			return false;
		}
	}
}
