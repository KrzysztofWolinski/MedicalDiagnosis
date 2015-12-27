package com.medica.core.domain.learn;


public class StringRange extends Range {

	private String name;
	
	public StringRange(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isInRange(String value) {
		return this.name.equals(value);
	}
}
