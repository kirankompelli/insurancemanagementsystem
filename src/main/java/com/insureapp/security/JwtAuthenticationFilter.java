package com.insureapp.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.insureapp.service.impl.CustomerDetailsService;
import com.insureapp.utils.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtil jwtUtil;
	
	 @Autowired
	    private CustomerDetailsService userDetailsService;

	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		 String authheader=request.getHeader("Authorization");
		 
		 String token=null;
		 String email=null;
		 
		 
		 if(authheader !=null && authheader.startsWith("Bearer ")) {
			 token=authheader.substring(7);
			 try {
				 email=jwtUtil.extractUsername(token);
			 }
			 catch(Exception e) {
				 logger.error("JWT token parsing error: " + e.getMessage()); 
			 }
		 }
		
		 if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			 UserDetails userDetails=userDetailsService.loadUserByUsername(email);
			 
			  if(jwtUtil.validateToken(token, userDetails)) {
				  UsernamePasswordAuthenticationToken authToken=new 
						  UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
			         authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			         SecurityContextHolder.getContext().setAuthentication(authToken);
			  }
			  
		 }
		 filterChain.doFilter(request, response);
		
		
	}

}
