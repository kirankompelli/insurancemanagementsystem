package com.insureapp.service.impl;



import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.insureapp.dto.AdminRegisterRequest;
import com.insureapp.dto.LoginRequest;
import com.insureapp.dto.LoginResponse;
import com.insureapp.dto.RegisterRequest;
import com.insureapp.entity.Role;
import com.insureapp.entity.User;
import com.insureapp.repository.RoleRepository;
import com.insureapp.repository.UserRepository;
import com.insureapp.service.UserService;
import com.insureapp.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService{
	
	@Autowired
	private RoleRepository rolerepo;
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private AuthenticationManager authenticationmanager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private PasswordEncoder passwordencoder;

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
                .password(passwordencoder.encode(request.getPassword()))
                .mobileNumber(request.getMobileNumber())
                .gender(request.getGender())
                .address(request.getAddress())
                .isActive(true)
                .roles(Collections.singleton(defaultRole))
                .build();
		return userrepo.save(user).toString();

		

	                
		
	}

	@Override
	public ResponseEntity<LoginResponse> login(LoginRequest request) {
		try {
		    Authentication   authentication=authenticationmanager.authenticate(
		       new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		    UserDetails userdetails =(UserDetails)authentication.getPrincipal();
		    
		    User user=userrepo.findByEmail(request.getEmail()).orElseThrow(()-> new RuntimeException("user not found"));
		    
		    String token=jwtUtil.generateToken((UserDetails)authentication.getPrincipal());
		    
		    String role=user.getRoles().stream().findFirst()
		    		.map(Role::getName).orElse("Unkown");
		    
		    return ResponseEntity.ok(LoginResponse.builder().token(token).message(role).build());
		   
		}
		catch(BadCredentialsException exception) {
			return ResponseEntity.badRequest().body(new LoginResponse(null, "Invalid Credentials"));
		}
		                   
		
	}

	@Override
	public String registerAdminOrAgent(AdminRegisterRequest request) {
		if (userrepo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Fetch role from DB
        Role role = rolerepo.findByName(request.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found: " + request.getRole()));

        // Build and save user
        User user = User.builder()
                .fullname(request.getFullname())
                .email(request.getEmail())
                .password(passwordencoder.encode(request.getPassword()))
                .mobileNumber(request.getMobileNumber())
                .gender(request.getGender())
                .address(request.getAddress())
                .isActive(true)
                .roles(Collections.singleton(role))
                .build();

        userrepo.save(user);
        return "User registered successfully with role: " + request.getRole();
	}

}
