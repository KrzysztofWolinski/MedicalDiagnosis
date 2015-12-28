package com.medica.core.service.learn.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.medica.core.domain.CoreDataPiece;
import com.medica.core.domain.DataPieceValueType;
import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.learn.NumberRange;
import com.medica.core.domain.learn.Range;
import com.medica.core.domain.learn.StringRange;
import com.medica.core.domain.rule.DiagnosisCoreRule;
import com.medica.core.domain.rule.components.AndContainer;
import com.medica.core.domain.rule.components.Expression;
import com.medica.core.domain.rule.components.Operators;
import com.medica.core.domain.rule.components.OrContainer;
import com.medica.core.domain.rule.components.SimpleExpression;
import com.medica.core.service.learn.LearnService;

public class DefaultLearnService implements LearnService {

	private static final String TOTAL_COUNT_KEY = "TOTAL";
	
	private final int DIV_COUNT;
	private final float TARGET_QUALIFYING_SHARE;
	private final float MIN_QUALIFYING_SHARE;
	
	public DefaultLearnService(int divCount, float targetQualifyingShare, float minQualifyingShare) {
		this.DIV_COUNT = divCount;
		this.TARGET_QUALIFYING_SHARE = targetQualifyingShare;
		this.MIN_QUALIFYING_SHARE = minQualifyingShare;
	}
	
	@Override
	public List<DiagnosisCoreRule> generateRules(List<DiagnosisCoreData> allDataList) {
		List<DiagnosisCoreData> filteredData = filterOutNonRatedData(allDataList);
		
		Map<String, DataPieceValueType> attibutesInfo = getAttibutesInfo(filteredData);
		Set<String> conditions = getConditions(filteredData);
		
		Map<String, Map<String, List<Range>>> dataRanges = new HashMap<String, Map<String, List<Range>>>();
		
		for (Map.Entry<String, DataPieceValueType> attributeInfo : attibutesInfo.entrySet()) {
			
			if (attributeInfo.getValue().equals(DataPieceValueType.STRING)) {
				Set<String> values = filteredData.stream().map(d -> d.getDataPiece(attributeInfo.getKey()).getValue()).distinct().collect(Collectors.toSet());
				Set<Range> ranges = values.stream().map(e -> new StringRange(e)).collect(Collectors.toSet());

				ranges.forEach(range -> {
					String currentAttributeValue = ((StringRange) range).getName();
					
					conditions.forEach(condition -> {
						long dataCount  = filteredData.stream().filter(d -> {
							boolean hasCondition = d.getDiagnosisResult().getConditionProbability().containsKey(condition); 
							boolean hasValue = d.getDataPiece(attributeInfo.getKey()).getValue().equals(currentAttributeValue);
							
							return hasCondition && hasValue;
						}).count();
						
						range.addCountValue(condition, dataCount);
					});
					
					long totalCount = filteredData.stream().filter(d -> d.getDataPiece(attributeInfo.getKey()).getValue().equals(currentAttributeValue)).count();
					range.addCountValue(TOTAL_COUNT_KEY, totalCount);
				});

				conditions.forEach(condition -> {
					float currentQualifyingShare = TARGET_QUALIFYING_SHARE;
					
					while (currentQualifyingShare >= MIN_QUALIFYING_SHARE) {
						List<Range> qualifyingRanges = new ArrayList<Range>(); 
								
						// TODO refactor
						for (Range range : ranges) {
							BigDecimal totalCount = new BigDecimal(range.getCount(TOTAL_COUNT_KEY));
							BigDecimal conditionCount = new BigDecimal(range.getCount(condition));
							
							BigDecimal share = conditionCount.divide(totalCount, 5, RoundingMode.HALF_UP);
							
							if (share.floatValue() >= currentQualifyingShare) {
								qualifyingRanges.add(range);
							}
						}
						
						if (qualifyingRanges.isEmpty()) {
							currentQualifyingShare--;
							continue;
						} else {
							if (dataRanges.get(condition) == null) {
								Map<String, List<Range>> attributeMap = new HashMap<String, List<Range>>();
								attributeMap.put(attributeInfo.getKey(), qualifyingRanges);
								
								dataRanges.put(condition, attributeMap);
							} else if (dataRanges.get(condition).get(attributeInfo.getKey()) == null) {
								dataRanges.get(condition).put(attributeInfo.getKey(), qualifyingRanges);
							} else {
								dataRanges.get(condition).get(attributeInfo.getKey()).addAll(qualifyingRanges);
							}
							
							break;
						}
					}
				});
				
			} else if (attributeInfo.getValue().equals(DataPieceValueType.NUMBER)) {
				List<Float> values = filteredData.stream().map(d -> Float.valueOf(d.getDataPiece(attributeInfo.getKey()).getValue())).collect(Collectors.toList());
				Float minValue = values.stream().min((v1, v2) -> v1 < v2 ? -1 : v1 > v2 ? 1 : 0).get();
				Float maxValue = values.stream().max((v1, v2) -> v1 < v2 ? -1 : v1 > v2 ? 1 : 0).get();
				Float valueSpan = maxValue - minValue;
				
				int currentRangeCount = DIV_COUNT;
				Set<Range> ranges = new HashSet<Range>();
				
				// prepare counts
				for (int i = 0; i < currentRangeCount; i++) {
					NumberRange range = new NumberRange();
					
					range.setMinValue(minValue + (i * (valueSpan / currentRangeCount)));
					range.setMaxValue(range.getMinValue() + (valueSpan / currentRangeCount));
					
					ranges.add(range);
				}
				
				ranges.forEach(range -> {
					conditions.forEach(condition -> {
						long dataCount  = filteredData.stream().filter(d -> {
							Float currentValue = Float.parseFloat(d.getDataPiece(attributeInfo.getKey()).getValue());
							
							boolean hasCondition = d.getDiagnosisResult().getConditionProbability().containsKey(condition); 
							boolean hasValue = ((NumberRange) range).isInRange(currentValue);
							
							return hasCondition && hasValue;
						}).count();
						
						range.addCountValue(condition, dataCount);
					});
					
					long totalCount = filteredData.stream().filter(d -> {
						Float currentValue = Float.parseFloat(d.getDataPiece(attributeInfo.getKey()).getValue());
						return ((NumberRange) range).isInRange(currentValue);
					}).count();
					
					range.addCountValue(TOTAL_COUNT_KEY, totalCount);
				});
					
				// filter ranges
				conditions.forEach(condition -> {
					float currentQualifyingShare = TARGET_QUALIFYING_SHARE;
					
					while (currentQualifyingShare >= MIN_QUALIFYING_SHARE) {
						List<Range> qualifyingRanges = new ArrayList<Range>();
						
						// TODO refactor
						for (Range range : ranges) {
							BigDecimal totalCount = new BigDecimal(range.getCount(TOTAL_COUNT_KEY));
							BigDecimal conditionCount = new BigDecimal(range.getCount(condition));
							
							BigDecimal share = BigDecimal.ZERO;
							if (!totalCount.equals(BigDecimal.ZERO)) {
								share = conditionCount.divide(totalCount, 5, RoundingMode.HALF_UP);
							}
							
							if (share.floatValue() >= currentQualifyingShare) {
								qualifyingRanges.add(range);
							}
						}
						
						if (qualifyingRanges.isEmpty()) {
							currentQualifyingShare--;
							continue;
						} else {
							
							qualifyingRanges = mergeAdjacentRanges(qualifyingRanges);
							
							if (dataRanges.get(condition) == null) {
								Map<String, List<Range>> attributeMap = new HashMap<String, List<Range>>();
								attributeMap.put(attributeInfo.getKey(), qualifyingRanges);
								
								dataRanges.put(condition, attributeMap);
							} else if (dataRanges.get(condition).get(attributeInfo.getKey()) == null) {
								dataRanges.get(condition).put(attributeInfo.getKey(), qualifyingRanges);
							} else {
								dataRanges.get(condition).get(attributeInfo.getKey()).addAll(qualifyingRanges);
							}
							
							break;
						}
					}
					
				});
				
				
				
			}
			
		}
		
		List<DiagnosisCoreRule> rules = convertRangesToRules(dataRanges);
		
		return evaluateRulesProbability(rules, filteredData);
	}

	private List<DiagnosisCoreData> filterOutNonRatedData(List<DiagnosisCoreData> allDataList) {
		List<DiagnosisCoreData> filteredData = allDataList.stream()
				.filter(d -> ((d.getDiagnosisResult() != null) && (d.getDiagnosisResult().isRated()))).collect(Collectors.toList());
		
		return filteredData;
	}
	
	private Set<String> getConditions(List<DiagnosisCoreData> allDataList) {
		Set<String> conditionsSet = allDataList.stream().flatMap(d -> d.getDiagnosisResult().getConditionProbability().keySet().stream()).collect(Collectors.toSet());
		return conditionsSet;
	}
	
	private Map<String, DataPieceValueType> getAttibutesInfo(List<DiagnosisCoreData> filteredData) {
		if (!filteredData.isEmpty()) {
			DiagnosisCoreData referenceData = filteredData.get(0);
			Set<String> names = referenceData.getDataMap().keySet();
			
			Map<String, DataPieceValueType> map = new HashMap<>();
			for (String name : names) {
				CoreDataPiece dataPiece = referenceData.getDataPiece(name);
				
				try {
					Float.valueOf(dataPiece.getValue());
					map.put(name, DataPieceValueType.NUMBER);
				} catch (NumberFormatException e) {
					map.put(name, DataPieceValueType.STRING);
				}
			}
			
			return map;
		} else {
			return new HashMap<String, DataPieceValueType>();
		}
	}
	
	private List<Range> mergeAdjacentRanges(List<Range> ranges) {
		List<Range> mergedRanges = new ArrayList<Range>();

		List<NumberRange> numberRanges = ranges.stream()
				.map(r -> (NumberRange) r)
				.sorted((r1, r2) -> Math.round(r1.getMinValue() - r2.getMinValue()))
				.collect(Collectors.toList());
		
		for (NumberRange range : numberRanges) {
			if (mergedRanges.isEmpty()) {
				mergedRanges.add(range);
			} else {
				NumberRange lastMergedRange = (NumberRange)(mergedRanges.get(mergedRanges.size() - 1));
				if (lastMergedRange.getMaxValue().equals(range.getMinValue())) {
					lastMergedRange.setMaxValue(range.getMaxValue());
				} else {
					mergedRanges.add(range);
				}
			}
		}
		
		return mergedRanges;
	}
	
	private List<DiagnosisCoreRule> convertRangesToRules(Map<String, Map<String, List<Range>>> dataRanges) {
		List<DiagnosisCoreRule> rules = new ArrayList<>();
		
		for (Map.Entry<String, Map<String, List<Range>>> conditionMap : dataRanges.entrySet()) {
			DiagnosisCoreRule newRule = new DiagnosisCoreRule();
			newRule.setDecision(conditionMap.getKey());
			
			List<Expression> expressions = conditionMap.getValue().entrySet().stream().map(attributeRanges -> {
				OrContainer container = new OrContainer();
				
				attributeRanges.getValue().forEach(range -> {					
					if (range instanceof StringRange) {
						SimpleExpression expression = new SimpleExpression();
						
						expression.setAttributeName(attributeRanges.getKey());
						expression.setOperator(Operators.EQUAL);
						expression.setValue(((StringRange) range).getName());
						
						container.addExpression(expression);
					} else if (range instanceof NumberRange) {
						AndContainer mainExpression = new AndContainer();
						
						SimpleExpression smallerExpression = new SimpleExpression();
						smallerExpression.setAttributeName(attributeRanges.getKey());
						smallerExpression.setOperator(Operators.SMALLER_THAN);
						smallerExpression.setValue(((NumberRange) range).getMaxValue().toString());
						
						SimpleExpression greaterExpression = new SimpleExpression();
						greaterExpression.setAttributeName(attributeRanges.getKey());
						greaterExpression.setOperator(Operators.GREATER_THAN);
						greaterExpression.setValue(((NumberRange) range).getMinValue().toString());
						
						mainExpression.addExpression(smallerExpression);
						mainExpression.addExpression(greaterExpression);
						
						container.addExpression(mainExpression);
					}
				});
				
				if (container.getExpressions().size() > 1) {
					return container;
				} else {
					return container.getExpressions().get(0);
				}
			}).collect(Collectors.toList());

			if (expressions.size() > 1) {
				OrContainer conditionExpression = new OrContainer();
				conditionExpression.setExpressions(expressions);
				
				newRule.setExpression(conditionExpression);
			} else {
				newRule.setExpression(expressions.get(0));
			}
			
			rules.add(newRule);
		}
		
		return rules;
	}
	
	private List<DiagnosisCoreRule> evaluateRulesProbability(List<DiagnosisCoreRule> rules, List<DiagnosisCoreData> dataList) {
		
		rules.forEach(rule -> {
			int correctAsignmentCount = 0;
			int incorrectAsignmentCount = 0;
			
			for (DiagnosisCoreData data : dataList) {
				boolean result = rule.evaluate(data);
				
				if (data.getDiagnosisResult().getConditionProbability().containsKey(rule.getDecision()) == result) {
					correctAsignmentCount++;
				} else {
					incorrectAsignmentCount++;
				}
			}
			
			int probability = (100 * correctAsignmentCount) / (correctAsignmentCount + incorrectAsignmentCount);
			
			rule.setProbability(probability);
		});
		
		return rules;
	}
}
