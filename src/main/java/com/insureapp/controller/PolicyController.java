package com.insureapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insureapp.dto.request.PolicyRequest;
import com.insureapp.dto.response.PolicyResponse;
import com.insureapp.service.PolicyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/policies")
@RequiredArgsConstructor
@Tag(name="Policy Management",description = "CRUD operations for insurance policies")
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
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'AGENT', 'CUSTOMER')")
	@Operation(summary = "Get all available policies", description = "Accessible to Admin, Agent, and Customer")
	@ApiResponses({
	    @ApiResponse(responseCode = "200", description = "List of policies returned successfully"),
	    @ApiResponse(responseCode = "403", description = "Access denied")
	})
	public ResponseEntity<List<PolicyResponse>> getAllPolicies(){
		return ResponseEntity.ok(service.getAllPolicies());
		
	}
	
	@Operation(summary = "Get a policy by ID", description = "Accessible by Admin, Agent, and Customer")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Policy fetched successfully"),
        @ApiResponse(responseCode = "404", description = "Policy not found")
    })
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'AGENT', 'CUSTOMER')")
	public ResponseEntity<PolicyResponse> getPolicyById(@Parameter(description="ID of the policy to fetch", example = "1")@PathVariable Long id){
	
		return ResponseEntity.ok(service.getPolicyById(id));
	}
	@Operation(summary = "Update a policy", description = "Accessible by Admin and Agent")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Policy updated successfully"),
        @ApiResponse(responseCode = "404", description = "Policy not found")
    })
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PolicyResponse> updatePolicy( @Parameter(description = "ID of the policy to update", example = "1") @PathVariable Long id,@RequestBody PolicyRequest request){
		
		return ResponseEntity.ok(service.updatePolicy(id, request));
	}
	
	 @Operation(summary = "Delete a policy", description = "Accessible by Admin and Agent")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Policy deleted successfully"),
	        @ApiResponse(responseCode = "404", description = "Policy not found")
	    })
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deletePolicy(@Parameter(description = "ID of the policy to update", example = "1") @PathVariable Long id){
		return ResponseEntity.ok(service.deletePolicy(id));
	}

}
