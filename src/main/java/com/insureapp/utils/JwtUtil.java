package com.insureapp.utils;

import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private long expiration;
	
	private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
    }
	
	public String generateToken(UserDetails userdetails) {
		return Jwts.builder()
				 .setSubject(userdetails.getUsername())
				 .setIssuedAt(new Date())
				 .setExpiration(new Date(System.currentTimeMillis()+expiration))
				 .signWith(SignatureAlgorithm.HS256, getSigningKey())
				 .compact();
	}
	
	public boolean validateToken(String token,UserDetails userdetails) {
		
		String username=extractUsername(token);
		return username.equals(userdetails.getUsername()) && !isTokenExpired(token);
		
	}
	
	public String extractUsername(String token) {
		       return     extractClaims(token, Claims::getSubject);
	}
	
	public boolean isTokenExpired(String token) {
		return extractExpiraction(token).before(new Date());
	}
	
	public Date extractExpiraction(String token) {
		return extractClaims(token,Claims::getExpiration);
	}

	public <T> T extractClaims(String token,Function<Claims,T> claimsResolver) {
		
		final Claims claims=Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token).getBody();
		return claimsResolver.apply(claims);
	}
}
