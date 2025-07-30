package com.insureapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	
public class LoginResponse {
	
	    @Schema(description = "JWT access token")
	    private String token;
	    
	    @Schema(description = "Success Message",example="Login successful")
	    private String message;
	
}


