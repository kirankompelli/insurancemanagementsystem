package com.insureapp.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
public class PolicyResponse {
	
	private Long id;

    private String policyname;

    private String description;

    private Double coverageamount;

    private Double premium;

    private int tenureinyears;

    private LocalDate startdate;

    private LocalDate enddate;

    private LocalDateTime createdat;

}
