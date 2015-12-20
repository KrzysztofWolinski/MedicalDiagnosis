package com.medica.core.domain;

import java.util.Date;


public class CoreByNameDataPiece {

	private String name;
	
	private Date dateSubmitted;
	
	private String value;

	public CoreByNameDataPiece() {
		
	}
	
	public CoreByNameDataPiece(String name,Date dateSubmitted, String value) {
		this.name = name;
		this.dateSubmitted = dateSubmitted;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
