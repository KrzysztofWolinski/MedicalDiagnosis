package com.medica.integration.service.auth.exceptions;

public class SessionExpiredException extends Exception {

	private static final long serialVersionUID = -3794414618579335316L;

	public SessionExpiredException() {
		super();
	}
	
	public SessionExpiredException(String msg) {
		super(msg);
	}
	
}
