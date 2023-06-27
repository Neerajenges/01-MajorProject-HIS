package com.eg.response;

import java.time.LocalDate;

import lombok.Data;
//Response Binding class
@Data
public class EligResponse {
	
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate  planEndDate;
	private Double benefitAmt;
	private String denialReason;

}
