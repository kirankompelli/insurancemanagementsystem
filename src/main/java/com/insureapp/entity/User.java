package com.insureapp.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message="fullname cannot be blank")
	private String fullname;
	
	@Column(nullable=false,unique=true)
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
	
    @Column(nullable = false, length = 10)
	private LocalDate dateofbirth;
	
	private String gender;
	
	@Column(length = 300)
	private String address;
	
	@Column(nullable = false)
	private Boolean isActive=true;
	
	@Column(nullable = false)
	private LocalDateTime createdat=LocalDateTime.now();
	
	@ManyToAny(fetch = FetchType.EAGER)
	@JoinTable(
		name="user_roles",
		joinColumns = @JoinColumn(name="user_id"),
		inverseJoinColumns=@JoinColumn(name="role_id")
			)
	private Set<Role> roles=new HashSet<>();
	
	
	
	
	

}
