package com.insureapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.insureapp.entity.User;
import com.insureapp.repository.UserRepository;

@Service
public class CustomerDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userrepo;
	@Override
	
	public UserDetails loadUserByUsername(String email) {
		 User user = userrepo.findByEmail(email)
                 .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
		 List<GrantedAuthority> authorities = user.getRoles().stream()
				    .map(role -> new SimpleGrantedAuthority(role.getName()))
				    .collect(Collectors.toList());

				return new CustomerUserDetails(user);
				      
		 
	}

	
}
