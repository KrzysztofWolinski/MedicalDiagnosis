package com.medica.integration.service.auth;

import com.medica.integration.service.auth.domain.AuthenticationCheckResult;
import com.medica.integration.service.auth.exceptions.InvalidCredentialsException;


public interface AuthService {

	public String login(String username, String password) throws InvalidCredentialsException;
	
	public void logout(String username, String token) throws InvalidCredentialsException;
	
	public AuthenticationCheckResult checkAuthentication(String username, String token);
	
	public boolean isAuthorized(String username, String token);
	
}
