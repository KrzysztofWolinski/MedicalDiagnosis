package com.medica.integration.service.history.domain;

public class HistoryConditionProbability {

	String diseaseName;
	
	Integer probability;

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public Integer getProbability() {
		return probability;
	}

	public void setProbability(Integer probability) {
		this.probability = probability;
	}
}
