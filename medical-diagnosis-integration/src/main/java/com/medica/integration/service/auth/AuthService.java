package com.medica.integration.service.auth;

import com.medica.integration.service.auth.exceptions.InvalidCredentialsException;


public interface AuthService {

	public String login(String username, String password) throws InvalidCredentialsException;
	
	public void logout(String username, String token) throws InvalidCredentialsException;
	
	public boolean checkAuthentication(String username, String token);
	
}
