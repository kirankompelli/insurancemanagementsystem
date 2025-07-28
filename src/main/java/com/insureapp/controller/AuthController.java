package com.insureapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insureapp.dto.RegisterRequest;
import com.insureapp.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AuthController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request ){
		           service.registerUser(request);
		           return ResponseEntity.ok("User registration sucessful");
	}

}
