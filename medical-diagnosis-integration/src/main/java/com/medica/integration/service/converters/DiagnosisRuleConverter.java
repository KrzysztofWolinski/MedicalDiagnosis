package com.medica.integration.service.converters;

import java.util.ArrayList;
import java.util.List;

import com.medica.core.domain.rule.DiagnosisCoreRule;
import com.medica.core.domain.rule.components.AndContainer;
import com.medica.core.domain.rule.components.Expression;
import com.medica.core.domain.rule.components.Operators;
import com.medica.core.domain.rule.components.OrContainer;
import com.medica.core.domain.rule.components.SimpleExpression;
import com.medica.integration.domain.diagnosis.DiagnosisRule;

public class DiagnosisRuleConverter {

	private static final char ELEMENT_SYMBOL = '\'';
	private static final char ELEMENT_SEPARATOR = ';';
	private static final char EXPRESSION_END_SYMBOL = ')';
	private static final char EXPRESSION_START_SYMBOL = '(';
	private static final String OR_SYMBOL = "OR";
	private static final String AND_SYMBOL = "AND";

	public static List<DiagnosisRule> convertToDao(List<DiagnosisCoreRule> inputRules) {
		List<DiagnosisRule> output = new ArrayList<DiagnosisRule>();
		
		if (inputRules != null) {
			for (DiagnosisCoreRule rule : inputRules) {
				output.add(convertToDao(rule));
			}
		}
		
		return output;
	}
	
	public static DiagnosisRule convertToDao(DiagnosisCoreRule inputRule) {
		DiagnosisRule output = new DiagnosisRule();
		
		output.setDecision(inputRule.getDecision());
		output.setProbability(inputRule.getProbability());
		output.setExpression(convertExpression(inputRule.getExpression()));
		
		return output;
	}
	
	public static List<DiagnosisCoreRule> convertToDto(List<DiagnosisRule> inputRules) {
		List<DiagnosisCoreRule> output = new ArrayList<DiagnosisCoreRule>();
		
		for (DiagnosisRule rule : inputRules) {
			output.add(convertToDto(rule));
		}
		
		return output;
	}
	
	public static DiagnosisCoreRule convertToDto(DiagnosisRule inputRule) {
		DiagnosisCoreRule output = new DiagnosisCoreRule();
		
		output.setDecision(inputRule.getDecision());
		output.setProbability(inputRule.getProbability());
		output.setExpression(convertExpression(inputRule.getExpression()));
		
		return output;
	}
	
	private static String convertExpression(Expression expression) {
		if (expression != null) {
			StringBuilder builder = new StringBuilder();
			
			if (expression instanceof SimpleExpression) {
				builder.append(ELEMENT_SYMBOL);
				builder.append(((SimpleExpression) expression).getAttributeName());
				builder.append(ELEMENT_SYMBOL);
				builder.append(((SimpleExpression) expression).getOperator());
				builder.append(ELEMENT_SYMBOL);
				builder.append(((SimpleExpression) expression).getValue());
				builder.append(ELEMENT_SYMBOL);				
			} else if (expression instanceof AndContainer) {
				builder.append(AND_SYMBOL);
				builder.append(EXPRESSION_START_SYMBOL);
				for (Expression e : ((AndContainer) expression).getExpressions()) {
					builder.append(convertExpression(e));
					builder.append(ELEMENT_SEPARATOR);
				}
				builder.append(EXPRESSION_END_SYMBOL);				
				
			} else if (expression instanceof OrContainer) {
				builder.append(OR_SYMBOL);
				builder.append(EXPRESSION_START_SYMBOL);
				for (Expression e : ((OrContainer) expression).getExpressions()) {
					builder.append(convertExpression(e));
					builder.append(ELEMENT_SEPARATOR);
				}
				builder.append(EXPRESSION_END_SYMBOL);	
			}
			
			return builder.toString();
		} else {
			return "";
		}
		
	}
	
	private static Expression convertExpression(String expression) {
		if ((expression != null) && (!expression.isEmpty())) {
			if (expression.startsWith(AND_SYMBOL)) {
				int beginIndex = expression.indexOf(EXPRESSION_START_SYMBOL);
				int endIndex = getClosingExpressionSymbolIndex(expression);
				
				String andExpression = expression.substring(beginIndex, endIndex);
				List<String> expressions = splitExpression(andExpression);
				
				AndContainer andContainer = new AndContainer();
				for (String e : expressions) {
					andContainer.addExpression(convertExpression(e));
				}
				
				return andContainer;
				
			} else if (expression.startsWith(OR_SYMBOL)) {
				int beginIndex = expression.indexOf(EXPRESSION_START_SYMBOL);
				int endIndex = getClosingExpressionSymbolIndex(expression);
				
				String orExpression = expression.substring(beginIndex, endIndex);
				List<String> expressions = splitExpression(orExpression);
				
				OrContainer orContainer = new OrContainer();
				for (String e : expressions) {
					orContainer.addExpression(convertExpression(e));
				}
				
				return orContainer;
				
			} else if (expression.startsWith(String.valueOf(ELEMENT_SYMBOL))) {
				int endIndex = expression.indexOf(ELEMENT_SEPARATOR);

				if (endIndex == -1) {
					endIndex = expression.length();
				}
				
				String simpleExpression = expression.substring(0, endIndex);
				
				return getSimpleExpressionFromString(simpleExpression);
			}
		} 
			
		return null;
	}
	
	private static int getClosingExpressionSymbolIndex(String expression) {
		int counter = 0;
		
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			
			if (c == EXPRESSION_START_SYMBOL) {
				counter++;
			} else if (c == EXPRESSION_END_SYMBOL) {
				counter--;
				
				if (counter == 0) {
					return i;
				}
			}
		}
		
		return -1;
	}
	
	private static List<String> splitExpression(String expression) {
		int counter = 0;
		List<Integer> indices = new ArrayList<Integer>();
		
		indices.add(1);
		
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			
			if (c == EXPRESSION_START_SYMBOL) {
				counter++;
			} else if (c == EXPRESSION_END_SYMBOL) {
				counter--;
			}
			
			if ((c == ELEMENT_SEPARATOR) && (counter == 1)) {
				indices.add(i + 1);
			}
		}
		
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < indices.size(); i++) {
			if (i < indices.size() - 1) {
				int startIndex = indices.get(i);
				int endIndex = indices.get(i + 1) - 1;
				result.add(expression.substring(startIndex, endIndex));
			}
		}
		
		return result;
	}
	
	private static SimpleExpression getSimpleExpressionFromString(String expression) {
		// Format: '<name>'<operator>'<value>'
		String[] elements = expression.split(String.valueOf(ELEMENT_SYMBOL));
		
		if (elements.length == 4) {
			SimpleExpression convertedExpression = new SimpleExpression();
			convertedExpression.setAttributeName(elements[1]);
			convertedExpression.setOperator(Operators.valueOf(elements[2]));
			convertedExpression.setValue(elements[3]);
			
			return convertedExpression;
		} else {
			return null;
		}
	}
}
