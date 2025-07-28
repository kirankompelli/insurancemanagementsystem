package com.insureapp.service.impl;



import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insureapp.dto.LoginRequest;
import com.insureapp.dto.LoginResponse;
import com.insureapp.dto.RegisterRequest;
import com.insureapp.entity.Role;
import com.insureapp.entity.User;
import com.insureapp.repository.RoleRepository;
import com.insureapp.repository.UserRepository;
import com.insureapp.service.UserService;

@Service
public class UserServiceImpl  implements UserService{
	
	@Autowired
	private RoleRepository rolerepo;
	
	@Autowired
	private UserRepository userrepo;

	@Override
	public String registerUser(RegisterRequest request) {
		if(userrepo.existsByEmail(request.getEmail())) {
			throw new RuntimeException("Email already exists");
		}
		Role defaultRole = rolerepo.findByName("ROLE_CUSTOMER")
			    .orElseThrow(() -> new RuntimeException("Default role not found"));

		User user = User.builder()
                .fullname(request.getFullname())
                .email(request.getEmail())
                .password(request.getPassword())
                .mobileNumber(request.getMobileNumber())
                .gender(request.getGender())
                .address(request.getAddress())
                .isActive(true)
                .roles(Collections.singleton(defaultRole))
                .build();
		return userrepo.save(user).toString();

		

	                
		
	}

	@Override
	public LoginResponse login(LoginRequest reuqest) {
		// TODO Auto-generated method stub
		return null;
	}

}
