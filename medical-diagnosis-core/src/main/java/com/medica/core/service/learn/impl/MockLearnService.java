package com.medica.core.service.learn.impl;

import java.util.Arrays;
import java.util.List;

import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.rule.DiagnosisCoreRule;
import com.medica.core.domain.rule.components.Operators;
import com.medica.core.domain.rule.components.SimpleExpression;
import com.medica.core.service.learn.LearnService;

public class MockLearnService implements LearnService {

	@Override
	public List<DiagnosisCoreRule> generateRules(List<DiagnosisCoreData> allDataList) {
		DiagnosisCoreRule mockRule = new DiagnosisCoreRule();
		
		SimpleExpression simpleExpression = new SimpleExpression();
		simpleExpression.setAttributeName("Temperature");
		simpleExpression.setOperator(Operators.GREATER_THAN);
		simpleExpression.setValue("38.0");
		
		mockRule.setDecision("FLU");
		mockRule.setProbability(100);
		mockRule.setExpression(simpleExpression);
		
		return Arrays.asList(mockRule);
	}

}
