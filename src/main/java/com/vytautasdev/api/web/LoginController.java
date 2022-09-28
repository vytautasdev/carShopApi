package com.vytautasdev.api.web;

import com.vytautasdev.api.domain.entity.AccountCredentials;
import com.vytautasdev.api.service.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final JwtService jwtService;

    final AuthenticationManager authenticationManager;

    public LoginController(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials) {
        var creds = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());

        var auth = authenticationManager.authenticate(creds);
        // Generate token
        String jwts = jwtService.getToken(auth.getName());
        // Build response with the generated token
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts).header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization").build();
    }
}
