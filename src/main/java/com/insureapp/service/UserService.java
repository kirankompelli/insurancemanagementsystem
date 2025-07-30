package com.insureapp.service;

import org.springframework.http.ResponseEntity;

import com.insureapp.dto.AdminRegisterRequest;
import com.insureapp.dto.LoginRequest;
import com.insureapp.dto.LoginResponse;
import com.insureapp.dto.RegisterRequest;
import com.insureapp.service.impl.CustomerUserDetails;

public interface UserService {

	String registerUser(RegisterRequest request, CustomerUserDetails agentUser);
	
	ResponseEntity<LoginResponse> login(LoginRequest reuqest);
	
	
	String registerAdminOrAgent(AdminRegisterRequest request);
}
