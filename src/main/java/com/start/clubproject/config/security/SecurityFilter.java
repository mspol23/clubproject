package com.start.clubproject.config.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.start.clubproject.services.LoginService;
import com.start.clubproject.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	TokenService tokenService;

	@Autowired
	LoginService loginService;
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		var token = this.recoverToken(request);
		System.out.println("doFilterInternal; token = " + token);
		if (token != null) {
			var email = tokenService.validateToken(token);
			System.out.println("doFilterInternal; email = " + email);
			if (!email.isEmpty()) {
				UserDetails login = loginService.findByEmail(email);
				System.out.println("doFilterInternal; login = " + login);
				if (login != null) {
					var authentication = new UsernamePasswordAuthenticationToken(login, null, login.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(authentication);
				} else { logger.error("Erro => LOGIN = NULL"); }
			} else { logger.error("Erro => EMAIL VAZIO!!!"); }
		}
		filterChain.doFilter(request, response);
	}

	private String recoverToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		System.out.println("recoverToken; authHeader = " + authHeader);
		if (authHeader == null)
			return null;
		return authHeader.replace("Bearer ", "").trim();
	}
	
	

}
