package com.edu.security;

import org.springframework.security.core.AuthenticationException;

public class NotAnyAuthoritiesException extends AuthenticationException {
	public NotAnyAuthoritiesException(String msg) {
		super(msg);
	}

	private static final long serialVersionUID = 1L;

}
