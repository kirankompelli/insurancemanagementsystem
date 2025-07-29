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
	@Size(min = 6, message = "Password must be at least 6 characters long")
	@Pattern(
		    regexp = "^(?=.*[A-Za-z])(?=.*\\d).{6,}$",
		    message = "Password must be at least 6 characters and contain at least one letter and one number"
		)
	private String password;

}
