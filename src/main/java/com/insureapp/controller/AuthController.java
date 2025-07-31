package com.insureapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insureapp.dto.LoginRequest;
import com.insureapp.dto.LoginResponse;
import com.insureapp.dto.RegisterRequest;
import com.insureapp.service.UserService;
import com.insureapp.service.impl.CustomerUserDetails;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/auth")
public class AuthController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/register-customer")
	@PreAuthorize("hasRole('AGENT')")
	@Operation(
			summary="Registers a new User",
			description="Agent is responsible to create an Customer"
	)
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description = "User Registration Successful"),
			@ApiResponse(responseCode="400",description = "invalid or input error")
	})
	public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request,@AuthenticationPrincipal CustomerUserDetails agentUser ){
		           service.registerUser(request,agentUser);
		           return ResponseEntity.ok("User registration sucessful");
	}
	
	
	@PostMapping("/login")
	@Operation(
		summary="userLogin",
		description = "authenticate the user by email and password , and returns a jwt token"
	)
	@ApiResponses( value= {
			@ApiResponse(responseCode = "200",description = "login successful"),
			@ApiResponse(responseCode = "401",description="Invalid email or password")
	})
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginrequest){
		
		 log.info("Login attempt for email: {}", loginrequest.getEmail());
		 return           service.login(loginrequest);
		
		
	}

}
