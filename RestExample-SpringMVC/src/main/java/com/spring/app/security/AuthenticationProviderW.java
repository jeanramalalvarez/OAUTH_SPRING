package com.spring.app.security;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.app.entity.Usuario;
import com.spring.app.service.UsuarioService;

@Service
public class AuthenticationProviderW implements AuthenticationProvider, Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationProviderW.class);

	@Autowired
	private UsuarioService userDetailsServices;

	@Override
	public Authentication authenticate(Authentication authentication) throws UsernameNotFoundException, BadCredentialsException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		logger.info("USERNAME: " + username + " --- PASSWORD: " + password);

		Usuario user = null;
		user = (Usuario) userDetailsServices.loadUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Usuario no existe");
		}
		//logger.info("USERNAME: " + username + " --- PASSWORD: " + password + " === " + Util.getStringMessageDigest(password, Util.SHA256) + "  BD PASS: " + user.getPassword());
		
		if (!password.equals(user.getPassword())) {
			throw new BadCredentialsException("Password Erroneo");
		}
		
		/*
		if (!Util.getStringMessageDigest(password, Util.SHA256).equals(user.getPassword())) {
			throw new BadCredentialsException("Password Erroneo");
		}
		*/

		return new UsernamePasswordAuthenticationToken(username, password, user != null ? user.getAuthorities() : null);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}