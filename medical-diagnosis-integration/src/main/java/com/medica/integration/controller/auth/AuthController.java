package com.medica.integration.controller.auth;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.medica.integration.controller.auth.domain.AuthRequestDto;
import com.medica.integration.controller.auth.domain.AuthResponseDto;
import com.medica.integration.controller.auth.domain.AuthResponseStatus;
import com.medica.integration.service.auth.AuthService;
import com.medica.integration.service.auth.exceptions.InvalidCredentialsException;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Inject
	private AuthService authService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public AuthResponseDto login(@RequestBody AuthRequestDto request) {
		AuthResponseDto response = new AuthResponseDto();
		
		try {
			String token = authService.login(request.getUsername(), request.getPassword());
			
			response.setStatus(AuthResponseStatus.OK);
			response.setToken(token);
		} catch(InvalidCredentialsException e) {
			response.setStatus(AuthResponseStatus.ERROR);
			response.setToken("");
		}
		
		return response;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public AuthResponseDto logout(@RequestBody AuthRequestDto request) {
		AuthResponseDto response = new AuthResponseDto();

		try {
			authService.logout(request.getUsername(), request.getToken());
			response.setStatus(AuthResponseStatus.OK);
		} catch (InvalidCredentialsException e) {
			response.setStatus(AuthResponseStatus.ERROR);
		}		
		
		return response;
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public AuthResponseDto checkAuthorization(@RequestBody AuthRequestDto request) {
		AuthResponseDto response = new AuthResponseDto();

		boolean authenticated = authService.checkAuthentication(request.getUsername(), request.getToken());

		if (authenticated) {
			response.setStatus(AuthResponseStatus.OK);
		} else {
			response.setStatus(AuthResponseStatus.ERROR);
		}				
		
		return response;
	}
	
}
