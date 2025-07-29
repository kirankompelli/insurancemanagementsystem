package com.insureapp.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import com.insureapp.entity.Role;
import com.insureapp.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
public class RegisterRequest {
	
	
	
	
	@NotBlank(message="fullname cannot be blank")
	private String fullname;
	
	
	@Email(message = "invalid email format")
	@NotBlank(message="email cannot be blank")
	private String email;
	
	@NotBlank(message="password cannot be blank")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	@Pattern(
		    regexp = "^[A-Za-z\\d@$!%*#?&]{6,}$",
		    message = "Password must be at least 6 characters"
		)
	private String password;
	
	@Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
    private String mobileNumber;
	
	private String gender;
	
	@Column(length = 300)
	private String address;
	
	
	
	
	
	
	
	


}
