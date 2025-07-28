package com.insureapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginRequest {
	
	
	@Email(message = "invalid email format")
	@NotBlank(message="email cannot be blank")
	private String email;
	
	@NotBlank(message="password cannot be blank")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	@Pattern(
	        regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
	        message = "Password must contain at least one letter, one number, and one special character"
	    )
	private String password;

}
