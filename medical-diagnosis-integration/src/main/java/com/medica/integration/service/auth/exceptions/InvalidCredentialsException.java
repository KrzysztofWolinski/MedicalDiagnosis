package com.medica.integration.service.auth.exceptions;

public class InvalidCredentialsException extends Exception {

	private static final long serialVersionUID = -6372375570016964713L;

	public InvalidCredentialsException() {
		super();
	}
	
	public InvalidCredentialsException(String msg) {
		super(msg);
	}
	
}
