package com.edu.security;

import org.springframework.security.core.AuthenticationException;

public class NotSupportAuthenticationException extends AuthenticationException {
	
	public NotSupportAuthenticationException(String msg) {
		super(msg);
	}

	private static final long serialVersionUID = 1L;
}
