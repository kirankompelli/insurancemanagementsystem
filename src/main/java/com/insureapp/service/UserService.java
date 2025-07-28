package com.insureapp.service;

import com.insureapp.dto.LoginRequest;
import com.insureapp.dto.LoginResponse;
import com.insureapp.dto.RegisterRequest;

public interface UserService {

	String registerUser(RegisterRequest request);
	
	LoginResponse login(LoginRequest reuqest);
}
