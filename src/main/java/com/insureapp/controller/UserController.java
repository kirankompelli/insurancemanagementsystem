package com.insureapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insureapp.entity.User;
import com.insureapp.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	
	@Autowired
	private UserRepository userrepo;
	
	
	@GetMapping("/me")
	@PreAuthorize("hasRole('ADMIN,AGENT,CUSTOMER')")
	public ResponseEntity<User> getLoggedInUser(Authentication authentication){
		           String email= authentication.getName();
		       User user=    userrepo.findByEmail(email)
		           .orElseThrow(() -> new RuntimeException("User not found"));
		       return ResponseEntity.ok(user);
		
	}

}
