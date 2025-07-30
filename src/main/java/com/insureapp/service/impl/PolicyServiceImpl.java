package com.insureapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insureapp.dto.request.PolicyRequest;
import com.insureapp.dto.response.PolicyResponse;
import com.insureapp.entity.Policy;
import com.insureapp.repository.PolicyRepository;
import com.insureapp.service.PolicyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
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

	public List<PolicyResponse> getAllPolicies() {
		 List<Policy> policies = policyrepo.findAll();
		    List<PolicyResponse> responses = new ArrayList<>();

		    for (Policy policy : policies) {
		        PolicyResponse response = PolicyResponse.builder()
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

		        responses.add(response);
		    }

		    return responses;
    }

	@Override
	public PolicyResponse getPolicyById(Long id) {
		Policy policy=policyrepo.findById(id).orElseThrow(()->new RuntimeException("Policy not found with id " + id));
		return PolicyResponse.builder().coverageamount(policy.getCoverageamount())
				.createdat(policy.getCreatedAt())
				.description(policy.getDescription())
				.enddate(policy.getEndDate())
				.policyname(policy.getPolicyname())
				.premium(policy.getPremium())
				.startdate(policy.getStartDate())
				.tenureinyears(policy.getTenureinyears())
				.id(policy.getId())
				.build();
	}

	@Override
	public PolicyResponse updatePolicy(long id, PolicyRequest request) {
		       Policy policy=   policyrepo.findById(id).orElseThrow(()->new RuntimeException("Policy not Found with id"+id));
		       policy.setPolicyname(request.getPolicyname());
		       policy.setDescription(request.getDescription());
		       policy.setCoverageamount(request.getCoverageamount());
		       policy.setPremium(request.getPremium());
		       policy.setTenureinyears(request.getTenureinyears());
		       policy.setStartDate(request.getStartdate());
		       policy.setEndDate(request.getEnddate());

		       policyrepo.save(policy);
		       
		       return PolicyResponse.builder().coverageamount(policy.getCoverageamount())
						.createdat(policy.getCreatedAt())
						.description(policy.getDescription())
						.enddate(policy.getEndDate())
						.policyname(policy.getPolicyname())
						.premium(policy.getPremium())
						.startdate(policy.getStartDate())
						.tenureinyears(policy.getTenureinyears())
						.id(policy.getId())
						.build();
		       
	}

	@Override
	public String deletePolicy(long id) {
		           Policy policy=  policyrepo.findById(id).orElseThrow(()-> new RuntimeException("Policy not Found with id"+id));
		  policyrepo.delete(policy);
		  return "Policy Deleted sucesfully";
	}
	
	

	
}
