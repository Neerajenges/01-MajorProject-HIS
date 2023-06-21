package com.eg.service;

import java.util.List;
import java.util.Map;

import com.eg.binding.Child;
import com.eg.binding.DcSummary;
import com.eg.binding.Education;
import com.eg.binding.Income;
import com.eg.binding.PlanSelection;

public interface DcService {
	
	public Long loadCaseNum(Integer appId);
	
	public Map<Integer,String> getPlanNames();
	
	public Long savePlanSelection(PlanSelection planSelection);
	
	public Long saveIncomeData(Income income);
	
	public Long saveEducation(Education education);
	
	public Long saveChildren(List<Child> childs);
	
	public DcSummary getSummary(Long caseNumber);
	
}
