package com.medica.integration.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login() {
		// TODO mock
		return "auth:login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout() {
		// TODO mock
		return "auth:logout";
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String checkAuthorization() {
		// TODO mock		
		return "auth:checkAuthorization";
	}
	
}
