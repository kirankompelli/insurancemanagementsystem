package com.insureapp.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class PolicyRequest {
	
	    @NotBlank(message = "Policy name is required")
	    private String policyname;

	    @NotBlank(message = "Description is required")
	    private String description;

	    @NotNull(message = "Coverage amount is required")
	    @Positive(message = "Coverage amount must be positive")
	    private Double coverageamount;

	    @NotNull(message = "Premium is required")
	    @Positive(message = "Premium must be positive")
	    private Double premium;

	    @Min(value = 1, message = "Tenure must be at least 1 year")
	    private int tenureinyears;

	    @NotNull(message = "Start date is required")
	    private LocalDate startdate;

	    @NotNull(message = "End date is required")
	    private LocalDate enddate;

}
