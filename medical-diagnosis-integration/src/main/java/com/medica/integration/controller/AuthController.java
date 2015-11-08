package com.medica.integration.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.medica.integration.domain.auth.AuthRequestDto;
import com.medica.integration.domain.auth.AuthResponseDto;
import com.medica.integration.domain.auth.AuthResponseStatus;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public AuthResponseDto login(@RequestBody AuthRequestDto request) {
		// TODO mock
		AuthResponseDto response = new AuthResponseDto();
		response.setStatus(AuthResponseStatus.OK);
		response.setToken("Testowy_token");
		
		return response;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public AuthResponseDto logout(@RequestBody AuthRequestDto request) {
		// TODO mock
		AuthResponseDto response = new AuthResponseDto();
		response.setStatus(AuthResponseStatus.OK);
		
		return response;
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public AuthResponseDto checkAuthorization() {
		// TODO mock
		AuthResponseDto response = new AuthResponseDto();
		response.setStatus(AuthResponseStatus.OK);
		
		return response;
	}
	
}
