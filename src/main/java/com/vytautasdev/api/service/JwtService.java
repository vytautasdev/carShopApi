package com.vytautasdev.api.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtService {
    static final long EXPIRATION_TIME = 86400000; // 1 day in ms
    static final String PREFIX = "Bearer";
    // Generate secret key
    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Generate signed JWT token
    public String getToken(String username) {
        return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(key).compact();
    }

    // Get a token from request Authorization header, verify a token and get username
    public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null) {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token.replace(PREFIX, "")).getBody().getSubject();
        }
        return null;
    }
}
