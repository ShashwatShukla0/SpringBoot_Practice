package com.jwt.utils;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwt.service.JwtUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private TokenManager tokenManager;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		String tokenHeader = request.getHeader("Authorization");
		String username = null;
		String token = null;
		
		if(tokenHeader!= null && tokenHeader.startsWith("Bearer")) {
			
			token = tokenHeader.substring(7);
			try {
				username = tokenManager.getUsernameFromToken(token);
			} catch( IllegalArgumentException e) {
				System.out.println("Jwt Token is expired");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token is expired");
			}
			
		} else {
			System.out.println("Bearer String not found in token");
		}
		
		if(null!= username && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			if(tokenManager.validateJwtToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken 
				= new UsernamePasswordAuthenticationToken(
						userDetails, null,
						userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		
	}

}