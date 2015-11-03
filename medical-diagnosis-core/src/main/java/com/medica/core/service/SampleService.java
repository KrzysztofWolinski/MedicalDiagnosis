package com.medica.core.service;

import com.medica.core.domain.SampleObject;

public class SampleService {

	public String sampleValue() {
		return "Hello!1 Sample service here.";
	}
	
	public SampleObject sampleObject() {
		return new SampleObject();
	}
	
}
