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
	public ResponseEntity<String> registerByAdmin(@Valid @RequestBody AdminRegisterRequest request){
		       return ResponseEntity.ok(service.registerAdminOrAgent(request));        
	}

}
