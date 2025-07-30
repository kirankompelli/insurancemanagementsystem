package com.insureapp.service;

import com.insureapp.dto.request.PolicyRequest;
import com.insureapp.dto.response.PolicyResponse;

public interface PolicyService {
	
	 PolicyResponse createPolicy(PolicyRequest request);

}
