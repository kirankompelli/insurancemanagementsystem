package com.insureapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdminRegisterRequest extends RegisterRequest {
	
	@NotBlank(message = "Role must not be blank")
	private String role;

}
