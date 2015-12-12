package com.medica.core.domain.rule.components;

import java.util.ArrayList;
import java.util.List;

public abstract class ExpressionContainer {
	
	protected List<Expression> expressions; 
	
	public List<Expression> getExpressions() {
		return expressions;
	}

	public void setExpressions(List<Expression> expressions) {
		this.expressions = expressions;
	}
	
	public void addExpression(Expression expression) {
		if (this.expressions == null) {
			this.expressions = new ArrayList<Expression>();
		}
		
		this.expressions.add(expression);
	}
}
