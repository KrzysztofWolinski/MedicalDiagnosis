package com.medica.integration.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.medica.core.domain.SampleObject;
import com.medica.core.service.SampleService;

@RestController
@RequestMapping("/")
class SampleController {
	
	@Inject
	private SampleService sampleService;
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String sample() {  	
        return "Hello, it's SampleController";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String sampleTest() {
    	return sampleService.sampleValue();
    }
    
    @RequestMapping(value = "/{value}", method = RequestMethod.GET)
    public String sampleValueWithParam(@PathVariable String value) {
    	return "You have entered " + value;
    }
    
    @RequestMapping(value = "/object", method = RequestMethod.GET)
    public SampleObject sampleObject() {
    	return sampleService.sampleObject();
    }
}
