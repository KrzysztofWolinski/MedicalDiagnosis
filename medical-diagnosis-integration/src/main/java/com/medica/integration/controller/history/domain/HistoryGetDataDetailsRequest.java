package com.medica.integration.controller.history.domain;

public class HistoryGetDataDetailsRequest {
	
	private Long dataId;
	
	private String username; 

	private String token;
	
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

}
