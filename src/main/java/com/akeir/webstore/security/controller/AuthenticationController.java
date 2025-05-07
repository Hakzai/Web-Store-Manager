package com.akeir.webstore.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akeir.webstore.security.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Controller", description = "Supports operations: login")
@ApiResponses(value = { 
		@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json"))
})
public class AuthenticationController {
	
	private final AuthenticationService authService;
	
	public AuthenticationController(AuthenticationService authService)
	{
		this.authService = authService;
	}
	
	@PostMapping("/login")
	@Operation(summary = "Login", description = "Will perform login with a valid username and password which should be passed through Auth fields (neither header or body)")
	@ApiResponse(responseCode = "200", description = "Success: returns a valid authentication token string")
	@ApiResponse(responseCode = "401", description = "Could not authenticate: Wrong username or password", content = @Content(mediaType = "application/json"))
	public String authenticate(Authentication auth)
	{
		return authService.authenticate(auth);
	}
}
