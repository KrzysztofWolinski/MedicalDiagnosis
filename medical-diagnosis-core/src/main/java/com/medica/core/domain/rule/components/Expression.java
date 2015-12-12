package com.medica.core.domain.rule.components;

import com.medica.core.domain.DiagnosisCoreData;

public interface Expression {

	public Boolean evaluate(DiagnosisCoreData data);
	
}
