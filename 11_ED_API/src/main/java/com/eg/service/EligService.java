package com.eg.service;

import com.eg.response.EligResponse;

public interface EligService {
	
	public EligResponse determineEligibility(Long caseNum);

}
