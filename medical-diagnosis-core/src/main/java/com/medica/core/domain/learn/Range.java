package com.medica.core.domain.learn;

import java.util.HashMap;
import java.util.Map;

public abstract class Range {

	private Map<String, Long> conditionCountMap;

	public Range () {
		this.conditionCountMap = new HashMap<String, Long>();
	}
	
	public Map<String, Long> getCountMap() {
		return conditionCountMap;
	}

	public void setCountMap(Map<String, Long> countMap) {
		this.conditionCountMap = countMap;
	}
	
	public void addCountValue(String name, long dataCount) {
		Long sum = dataCount + (conditionCountMap.get(name) != null ? conditionCountMap.get(name) : 0);
		conditionCountMap.put(name, sum);
	}
	
}
