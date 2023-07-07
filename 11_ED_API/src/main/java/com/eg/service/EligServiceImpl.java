package com.eg.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eg.entity.CitizenAppEntity;
import com.eg.entity.CoTriggerEntity;
import com.eg.entity.DcCaseEntity;
import com.eg.entity.DcChildrenEntity;
import com.eg.entity.DcEducationEntity;
import com.eg.entity.DcIncomeEntity;
import com.eg.entity.EligDtlsEntity;
import com.eg.entity.PlanEntity;
import com.eg.exception.EdException;
import com.eg.repository.CitizenAppRepository;
import com.eg.repository.CoTriggerRepository;
import com.eg.repository.DcCaseRepo;
import com.eg.repository.DcChildrenRepo;
import com.eg.repository.DcEducationRepo;
import com.eg.repository.DcIncomeRepo;
import com.eg.repository.EligDtlsRepository;
import com.eg.repository.PlanRepo;
import com.eg.response.EligResponse;

@Service
public class EligServiceImpl implements EligService {

	@Autowired
	private DcCaseRepo dcCaseRepo;
	@Autowired
	private PlanRepo planRepo;
	@Autowired
	private DcIncomeRepo incomeRepo;
	@Autowired
	private DcChildrenRepo childRepo;
	@Autowired
	private CitizenAppRepository appRepo;
	@Autowired
	private DcEducationRepo eduRepo;
	@Autowired
	private EligDtlsRepository eligRepo;
	@Autowired
	private CoTriggerRepository coRepo;

	@Override
	public EligResponse determineEligibility(Long caseNum) {
		EligResponse eligResponse=null;
		
		try {
			Optional<DcCaseEntity> caseEntity = dcCaseRepo.findById(caseNum);
			Integer planId = null;
			String planName = null;
			Integer appId = null;

			if (caseEntity.isPresent()) {
				DcCaseEntity dcCaseEntity = caseEntity.get();
				planId = dcCaseEntity.getPlanId();
				appId = dcCaseEntity.getAppId();
			}
			Optional<PlanEntity> planEntity = planRepo.findById(planId);
			if (planEntity.isPresent()) {
				PlanEntity plan = planEntity.get();
				planName = plan.getPlanName();
			}
			Optional<CitizenAppEntity> app = appRepo.findById(appId);
			Integer age=0;
			CitizenAppEntity citizenAppEntity=null;
			if (app.isPresent()) {
				citizenAppEntity = app.get();
				LocalDate dob = citizenAppEntity.getDob();
				LocalDate now = LocalDate.now();
				age = Period.between(dob, now).getYears();
			}
			eligResponse = executePlanConditions(caseNum, planName, age);
			
			//logic to store in database
			EligDtlsEntity eligEntity=new EligDtlsEntity();
			BeanUtils.copyProperties(eligResponse, eligEntity);
			eligEntity.setCaseNo(caseNum);
			eligEntity.setHolderName(citizenAppEntity.getFullName());
			eligEntity.setHolderSsn(citizenAppEntity.getSsn());
			
			eligRepo.save(eligEntity);//Saving in ElidDtlsEntity table 
			
			CoTriggerEntity coEntity=new CoTriggerEntity();
			coEntity.setCaseNum(caseNum);
			coEntity.setTrgStatus("Pending");
			coRepo.save(coEntity);//saving in CoTriggerEntity table
		
			
		}catch(Exception e) {
			throw new EdException(e.getMessage());
			
		}
		return eligResponse;

		}

	private EligResponse executePlanConditions(Long caseNum, String planName, Integer age) {

		EligResponse response = new EligResponse();
		response.setPlanName(planName);
		DcIncomeEntity income = incomeRepo.findByCaseNum(caseNum);
		// logic to execute the conditions
		if ("SNAP".equals(planName)) {
			Double empIncome = income.getEmpIncome();
			if (empIncome <= 300) {
				response.setPlanStatus("AP");
			} else {
				response.setPlanStatus("DN");
				response.setDenialReason("High Income");
			}

		} else if ("CCAP".equals(planName)) {
			boolean ageCondition = true;
			boolean kidsCountCondition = false;
			List<DcChildrenEntity> childs = childRepo.findByCaseNum(caseNum);
			if (!childs.isEmpty()) {
				kidsCountCondition = true;
				for (DcChildrenEntity entity : childs) {
					Integer childAge = entity.getChildAge();
					if (childAge > 16) {
						ageCondition = false;
						break;
					}

				}
			}
			if (income.getEmpIncome() <= 300 && kidsCountCondition && ageCondition) {
				response.setPlanStatus("AP");
			} else {
				response.setPlanStatus("DN");
				response.setDenialReason("Not satisfied business rule");
			}

		} else if ("Medicaid".equals(planName)) {
			Double empIncome = income.getEmpIncome();
			Double propertyIncome = income.getPropertyIncome();
			if (empIncome <= 300 && propertyIncome == 0) {
				response.setPlanStatus("AP");
			} else {
				response.setPlanStatus("DN");
				response.setDenialReason("High Income");
			}

		} else if ("Medicare".equals(planName)) {
			

				if (age >= 65) {
					response.setPlanStatus("AP");
				} else {
					response.setPlanStatus("DN");
					response.setDenialReason("Age Not Matched");
				}

			
	

		} else if ("NJW".equals(planName)) {
			DcEducationEntity educationEntity = eduRepo.findByCaseNum(caseNum);
			Integer graduationYear = educationEntity.getGraduationYear();
			int currYear = LocalDate.now().getYear();
			if (income.getEmpIncome() <= 0 && graduationYear < currYear) {
				response.setPlanStatus("AP");
			} else {
				response.setPlanStatus("DN");
				response.setDenialReason("Rule Not satisfied");
			}

		}
		// setting dummy start date & end dates
		if (response.getPlanStatus().equals("AP")) {
			response.setPlanStartDate(LocalDate.now());
			response.setPlanEndDate(LocalDate.now().plusMonths(6));
			response.setBenefitAmt(350.00);
		}
		return response;
	}

}
