package com.insureapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insureapp.dto.LoginRequest;
import com.insureapp.dto.LoginResponse;
import com.insureapp.dto.RegisterRequest;
import com.insureapp.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AuthController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/register")
	@Operation(
			summary="Registers a new User",
			description="Creates a New User by acceptiong the user data with valid credentials"
	)
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description = "User Registration Successful"),
			@ApiResponse(responseCode="400",description = "invalid or input error")
	})
	public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request ){
		           service.registerUser(request);
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
		 return           service.login(loginrequest);
		
		
	}

}
