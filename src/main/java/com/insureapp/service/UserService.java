package com.insureapp.service;

import org.springframework.http.ResponseEntity;

import com.insureapp.dto.LoginRequest;
import com.insureapp.dto.LoginResponse;
import com.insureapp.dto.RegisterRequest;

public interface UserService {

	String registerUser(RegisterRequest request);
	
	ResponseEntity<LoginResponse> login(LoginRequest reuqest);
}
