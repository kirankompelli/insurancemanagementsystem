package com.insureapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insureapp.dto.request.PolicyRequest;
import com.insureapp.dto.response.PolicyResponse;
import com.insureapp.service.PolicyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/policies")
public class PolicyController {
	
	
	@Autowired
	private PolicyService service;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	 @Operation(summary = "Create a new insurance policy (Admin  only)")
	 @ApiResponses(value = {
		        @ApiResponse(responseCode = "201", description = "Policy created successfully"),
		        @ApiResponse(responseCode = "403", description = "Access denied"),
		        @ApiResponse(responseCode = "400", description = "Invalid input")
		    })
	public ResponseEntity<PolicyResponse> createPolicy(@Valid @RequestBody PolicyRequest request){
		 PolicyResponse response=service.createPolicy(request);
		 return new ResponseEntity<>(response,HttpStatus.CREATED);
	}

}
