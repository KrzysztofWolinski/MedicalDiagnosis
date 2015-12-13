package com.medica.core.service.learn.impl;

import java.util.Arrays;
import java.util.List;

import com.medica.core.domain.DiagnosisCoreResult;
import com.medica.core.domain.rule.DiagnosisCoreRule;
import com.medica.core.domain.rule.components.Operators;
import com.medica.core.domain.rule.components.SimpleExpression;
import com.medica.core.service.learn.LearnService;

public class MockLearnService implements LearnService {

	@Override
	public List<DiagnosisCoreRule> generateRules(List<DiagnosisCoreResult> allDataList) {
		DiagnosisCoreRule mockRule = new DiagnosisCoreRule();
		
		SimpleExpression simpleExpression = new SimpleExpression();
		simpleExpression.setAttributeName("TEST");
		simpleExpression.setOperator(Operators.EQUAL);
		simpleExpression.setValue("TEST_VALUE");
		
		mockRule.setDecision("TEST_DECISION");
		mockRule.setProbability(100);
		mockRule.setExpression(simpleExpression);
		
		return Arrays.asList(mockRule);
	}

}
