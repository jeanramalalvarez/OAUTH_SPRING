package com.spring.app.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SecurityLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	public SecurityLoginSuccessHandler() {
		super();
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
		response.setHeader("statuslogin", "0");
		request.getSession().setMaxInactiveInterval(30 * 60);
	}
}
