package com.insureapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agent")
public class AgentController {
	
	
	@GetMapping("/dashboard")
	public String agentDashboard() {
		
		return "Welcome Agent";
	}

}
