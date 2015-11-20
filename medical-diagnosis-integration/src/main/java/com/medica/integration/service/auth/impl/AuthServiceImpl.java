package com.medica.integration.service.auth.impl;

import java.util.Date;

import javax.inject.Inject;

import com.medica.integration.domain.user.Credentials;
import com.medica.integration.repository.CredentialsRepository;
import com.medica.integration.service.auth.AuthService;
import com.medica.integration.service.auth.TokenGeneratorService;
import com.medica.integration.service.auth.domain.AuthenticationCheckResult;
import com.medica.integration.service.auth.exceptions.InvalidCredentialsException;

public class AuthServiceImpl implements AuthService {

	@Inject
	private TokenGeneratorService tokenGeneratorService;
	
	@Inject
	private CredentialsRepository credentialsRepository;
	
	@Override
	public String login(String username, String password) throws InvalidCredentialsException {
		Credentials credentials = credentialsRepository.findByUsername(username);
		
		if ((credentials != null) && (credentials.getPassword().equals(password))) {
			credentials.setToken(tokenGeneratorService.generateToken());
			credentials.setTokenExpirationTime(tokenGeneratorService.generateTokenExpirationTime());
			
			credentialsRepository.saveAndFlush(credentials);
			
			return credentials.getToken();
		} else {
			throw new InvalidCredentialsException();
		}	
	}

	@Override
	public void logout(String username, String token) throws InvalidCredentialsException {
		Credentials credentials = credentialsRepository.findByUsername(username);
		
		if ((credentials != null) && (credentials.getToken().equals(token))) {
			credentials.setToken("");
			credentials.setTokenExpirationTime(new Date());
			
			credentialsRepository.saveAndFlush(credentials);
		} else {
			throw new InvalidCredentialsException();
		}
	}

	@Override
	public AuthenticationCheckResult checkAuthentication(String username, String token) {
		Credentials credentials = credentialsRepository.findByUsername(username);
		AuthenticationCheckResult result = new AuthenticationCheckResult();
		
		if ((credentials != null) && (credentials.getToken().equals(token))) {
			if ((credentials.getTokenExpirationTime() != null) && (credentials.getTokenExpirationTime().after(new Date()))) {
				updateTokenExpirationTime(credentials);
				
				return result.withStatusOk();
			} else {
				resetCredentials(credentials);
				
				return result.withStatusExpired();
			}
		} else {
			return result.withStatusInvalid();
		}
	}

	@Override
	public boolean isAuthorized(String username, String token) {
		Credentials credentials = credentialsRepository.findByUsername(username);
		
		if ((credentials != null) && (credentials.getToken().equals(token))) {
			return true;
		} else {
			return false;
		}
	}
	
	private void updateTokenExpirationTime(Credentials credentials) {
		credentials.setTokenExpirationTime(tokenGeneratorService.generateTokenExpirationTime());
		
		this.credentialsRepository.saveAndFlush(credentials);
	}
	
	private void resetCredentials(Credentials credentials) {
		credentials.setToken("");
		credentials.setTokenExpirationTime(null);
		
		this.credentialsRepository.saveAndFlush(credentials);
	}
}
