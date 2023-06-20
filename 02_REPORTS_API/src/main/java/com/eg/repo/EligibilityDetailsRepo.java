package com.eg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eg.entity.EligibilityDetails;

public interface EligibilityDetailsRepo extends JpaRepository<EligibilityDetails, Integer> {
	
	@Query("select distinct(planName) from EligibilityDetails")
	public List<String> findPlanNames();
	//we are recommended to write query in Repository interface
	@Query("select distinct(planStatus) from EligibilityDetails")
	public List<String> findPlanStatuses();

}
