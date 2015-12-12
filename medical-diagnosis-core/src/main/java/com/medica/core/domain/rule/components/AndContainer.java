package com.medica.core.domain.rule.components;

import com.medica.core.domain.DiagnosisCoreData;

public class AndContainer extends ExpressionContainer implements Expression {

	@Override
	public Boolean evaluate(DiagnosisCoreData data) {
		if ((expressions == null) || (expressions.isEmpty())) {
			return false;
		} else {
			return this.expressions.stream()
					.map(e -> e.evaluate(data))
					.allMatch(v -> v.equals(true));
		}
	}

}
