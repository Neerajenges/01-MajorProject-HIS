package com.eg.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchRequest {
	//by which field we want to search the in the table one or multiple constrants 
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;

}
