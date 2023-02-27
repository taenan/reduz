package com.taenan.reduz.api.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taenan.reduz.api.model.input.UserInput;
import com.taenan.reduz.core.security.TokenGenerator;
import com.taenan.reduz.domain.model.dto.TokenDTO;
import com.taenan.reduz.domain.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private TokenGenerator tokenGenerator;

	private DaoAuthenticationProvider daoAuthenticationProvider;

	@Qualifier("jwtRefreshTokenAuthProvider")
	private JwtAuthenticationProvider refreshTokenAuthProvider;

	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Valid UserInput userInput) {
		userService.register(userInput);
		UserDetails user = userService.loadUserByUsername(userInput.getUsername());
		Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(user, userInput.getPassword(),
				Collections.emptyList());

		return ResponseEntity.ok(tokenGenerator.createToken(authentication));
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid UserInput userInput) {
		Authentication authentication = daoAuthenticationProvider.authenticate(
				UsernamePasswordAuthenticationToken.unauthenticated(userInput.getUsername(), userInput.getPassword()));

		return ResponseEntity.ok(tokenGenerator.createToken(authentication));
	}

	@PostMapping("/token")
	public ResponseEntity<?> token(@RequestBody TokenDTO tokenDTO) {
		Authentication authentication = refreshTokenAuthProvider
				.authenticate(new BearerTokenAuthenticationToken(tokenDTO.getRefreshToken()));
		// Jwt jwt = (Jwt) authentication.getCredentials();

		return ResponseEntity.ok(tokenGenerator.createToken(authentication));
	}
	
}
