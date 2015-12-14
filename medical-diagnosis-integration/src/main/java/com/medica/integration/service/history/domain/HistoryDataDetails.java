package com.medica.integration.service.history.domain;

import java.util.List;

public class HistoryDataDetails {

	private HistoryByDateDataBlock dataBlock;

	private List<HistoryConditionProbability> conditionProbability;
	
	public HistoryByDateDataBlock getDataBlock() {
		return dataBlock;
	}

	public void setDataBlock(HistoryByDateDataBlock data) {
		this.dataBlock = data;
	}

	public List<HistoryConditionProbability> getConditionProbability() {
		return conditionProbability;
	}

	public void setConditionProbability(List<HistoryConditionProbability> conditionProbability) {
		this.conditionProbability = conditionProbability;
	}
	
}
