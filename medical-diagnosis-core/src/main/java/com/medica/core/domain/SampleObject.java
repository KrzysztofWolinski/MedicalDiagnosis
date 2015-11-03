package com.medica.core.domain;

public class SampleObject {
	private String name;
	private String sampleValue;
	
	public SampleObject() {
		this.name = "no_name";
		this.sampleValue = "";
	}
	
	public SampleObject(String name, String sampleValue) {
		this.name = name;
		this.sampleValue = sampleValue;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSampleValue() {
		return sampleValue;
	}
	
	public void setSampleValue(String sampleValue) {
		this.sampleValue = sampleValue;
	}
	
	
}
