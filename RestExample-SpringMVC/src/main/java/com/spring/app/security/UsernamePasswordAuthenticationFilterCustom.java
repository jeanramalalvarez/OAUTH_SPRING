package com.spring.app.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class UsernamePasswordAuthenticationFilterCustom extends UsernamePasswordAuthenticationFilter {

	private static final Logger logger = LoggerFactory.getLogger(UsernamePasswordAuthenticationFilterCustom.class);
	private boolean postOnly;


	@Override
	public void setPostOnly(boolean postOnly) {
		super.setPostOnly(postOnly);
		this.postOnly = postOnly;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (postOnly && !"POST".equals(request.getMethod())) {
			throw new AuthenticationServiceException("Metodo no Soportado - Authentication: " + request.getMethod());
		}

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}
		//String pass = Util.getStringMessageDigest(password, Util.SHA256);
		String pass = password;
		username = username.trim();
		logger.info("USERPASSWORDTOKEN: " + username + " - " + pass );

		final UsernamePasswordAuthenticationTokenCustom authRequest = new UsernamePasswordAuthenticationTokenCustom(
				username, pass);
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}
}