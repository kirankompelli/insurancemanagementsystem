package com.insureapp.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="policies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Policy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String policyname;
	
	@Column(nullable=false,length = 500)
	private String description;
	
	@Column(nullable = false)
    private double coverageamount;
	
	 @Column(nullable = false)
	    private double premium;
	 
	 @Column(nullable = false)
	    private int tenureinyears;
	 @Column(nullable = false)
	    private LocalDate startDate;

	    @Column(nullable = false)
	    private LocalDate endDate;
	    
	    @CreationTimestamp
	    @Column(nullable = false)
	    private LocalDateTime createdAt;


}
