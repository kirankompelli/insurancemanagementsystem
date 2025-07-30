package com.insureapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insureapp.dto.request.PolicyRequest;
import com.insureapp.dto.response.PolicyResponse;
import com.insureapp.entity.Policy;
import com.insureapp.repository.PolicyRepository;
import com.insureapp.service.PolicyService;

@Service
public class PolicyServiceImpl implements PolicyService{
	
	
    @Autowired
	private PolicyRepository policyrepo;
    
	public PolicyResponse createPolicy(PolicyRequest request) {
		 Policy policy = Policy.builder()
	                .policyname(request.getPolicyname())
	                .description(request.getDescription())
	                .coverageamount(request.getCoverageamount())
	                .premium(request.getPremium())
	                .tenureinyears(request.getTenureinyears())
	                .startDate(request.getStartdate())
	                .endDate(request.getEnddate())
	                .build();

	        policy = policyrepo.save(policy);
	        
	        
		return  PolicyResponse.builder()
                .id(policy.getId())
                .policyname(policy.getPolicyname())
                .description(policy.getDescription())
                .coverageamount(policy.getCoverageamount())
                .premium(policy.getPremium())
                .tenureinyears(policy.getTenureinyears())
                .startdate(policy.getStartDate())
                .enddate(policy.getEndDate())
                .createdat(policy.getCreatedAt())
                .build();
	}

}
