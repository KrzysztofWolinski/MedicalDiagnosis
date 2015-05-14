package com.bodziowagh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bodziowagh.sample.SampleService;

@Controller
class SampleController {
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String sample() {  	
        return "Hello, it's SampleController";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String sampleTest() {
    	SampleService sample = new SampleService();
    	return sample.sampleValue();
    }
}
