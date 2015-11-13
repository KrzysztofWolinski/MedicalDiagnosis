package com.medica.integration.service.auth.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;

import com.medica.integration.service.auth.TokenGeneratorService;

public class TokenGeneratorServiceImpl implements TokenGeneratorService {

	@Value("${auth.token.expiration_period}")
	private Long TOKEN_EXPIRATION_PERIOD; 
	
	@Value("${auth.token.length}")
	private Long TOKEN_LENGTH;
	
	private final String CHAR_POOL = "abcdefghijklmnopqrstuvwxyz0123456789";
	
	@Override
	public String generateToken() {
		StringBuilder token = new StringBuilder();
		Random random = new Random();
		char[] chars = CHAR_POOL.toCharArray();
		
		for (int i = 0; i < TOKEN_LENGTH; i++) {
			char nextChar = chars[random.nextInt(chars.length)];
			token.append(nextChar);
		}
		
		return token.toString();
	}

	@Override
	public Date generateTokenExpirationTime() {
		long currentTime = Calendar.getInstance().getTimeInMillis();
		Date expirationTime = new Date(currentTime + TOKEN_EXPIRATION_PERIOD);
		
		return expirationTime;
	}

}
