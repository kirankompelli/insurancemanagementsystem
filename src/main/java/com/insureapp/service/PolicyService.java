package com.insureapp.service;

import java.util.List;

import com.insureapp.dto.request.PolicyRequest;
import com.insureapp.dto.response.PolicyResponse;


public interface PolicyService {
	
	 PolicyResponse createPolicy(PolicyRequest request);
	 
	 List<PolicyResponse> getAllPolicies();
	 
	 PolicyResponse getPolicyById(Long id);
	 
	 PolicyResponse updatePolicy(long id,PolicyRequest request);
	 
	 String deletePolicy(long id);

}
