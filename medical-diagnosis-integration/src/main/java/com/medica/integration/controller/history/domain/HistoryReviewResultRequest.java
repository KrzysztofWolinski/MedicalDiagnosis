package com.medica.integration.controller.history.domain;

import java.util.List;

import com.medica.integration.service.history.domain.HistoryConditionProbability;

public class HistoryReviewResultRequest {

	private Long dataId;
	
	private String username; 

	private String token;
	
	private List<HistoryConditionProbability> newConditionProbabilities;
	
	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<HistoryConditionProbability> getNewConditionProbabilities() {
		return newConditionProbabilities;
	}

	public void setNewConditionProbabilities(
			List<HistoryConditionProbability> newConditionProbabilities) {
		this.newConditionProbabilities = newConditionProbabilities;
	}
	
}
