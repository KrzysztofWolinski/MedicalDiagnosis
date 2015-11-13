package com.medica.integration.service.auth;

import java.util.Date;

public interface TokenGeneratorService {

	public String generateToken();
	
	public Date generateTokenExpirationTime();
	
}
