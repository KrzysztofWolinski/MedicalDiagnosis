package com.bodziowagh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bodziowagh.sample.SampleService;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.bodziowagh.controller")
class RootConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	SampleService sampleService() {
		return new SampleService();
	}
}