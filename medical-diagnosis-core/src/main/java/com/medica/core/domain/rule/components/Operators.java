package com.medica.core.domain.rule.components;

public enum Operators {

	GREATER_THAN("GREATER_THAN"),
	SMALLER_THAN("SMALLER_THAN"),
	EQUAL("EQUAL");
	
	private final String name;
	
	Operators(String name) {
		this.name = name;
	}
	
	String getStatus() {
		return this.name;
	}
}
