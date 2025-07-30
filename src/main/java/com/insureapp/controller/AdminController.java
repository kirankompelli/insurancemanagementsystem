package com.insureapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insureapp.dto.AdminRegisterRequest;
import com.insureapp.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private UserService service;
	
	
	@GetMapping("/dashboard")
	public String adminDashBoard() {
		
		return "Welcome Admin";
	}
	
	@PostMapping("/register")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(
			summary="Admin or Agent Register",
			description = "Only admin can register the Admin or Agent Using the User Data")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "Registration Sucessful"),
			@ApiResponse(responseCode = "401",description = "Invalid or Input Error")
	})
	public ResponseEntity<String> registerByAdmin(@Valid @RequestBody AdminRegisterRequest request){
		       return ResponseEntity.ok(service.registerAdminOrAgent(request));        
	}

}
