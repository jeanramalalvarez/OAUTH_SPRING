package com.spring.app.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UsernamePasswordAuthenticationTokenCustom extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = -2109405049360688619L;

	public UsernamePasswordAuthenticationTokenCustom(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public UsernamePasswordAuthenticationTokenCustom(Object principal, Object credentials, String timeout) {
		this(principal, credentials);
	}
}