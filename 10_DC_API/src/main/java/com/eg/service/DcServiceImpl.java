package com.eg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eg.binding.Child;
import com.eg.binding.ChildRequest;
import com.eg.binding.DcSummary;
import com.eg.binding.Education;
import com.eg.binding.Income;
import com.eg.binding.PlanSelection;
import com.eg.entity.CitizenAppEntity;
import com.eg.entity.DcCaseEntity;
import com.eg.entity.DcChildrenEntity;
import com.eg.entity.DcEducationEntity;
import com.eg.entity.DcIncomeEntity;
import com.eg.entity.PlanEntity;
import com.eg.repository.CitizenAppRepository;
import com.eg.repository.DcCaseRepo;
import com.eg.repository.DcChildrenRepo;
import com.eg.repository.DcEducationRepo;
import com.eg.repository.DcIncomeRepo;
import com.eg.repository.PlanRepo;

@Service
public class DcServiceImpl implements DcService {
	
	@Autowired
	private DcCaseRepo dcCaseRepo;
	@Autowired
	private PlanRepo planRepo;
	@Autowired
	private DcIncomeRepo incomeRepo;
	@Autowired
	private DcEducationRepo eduRepo;
	@Autowired
	private DcChildrenRepo childRepo;
	@Autowired
	private CitizenAppRepository appRepo;

	@Override
	public Long loadCaseNum(Integer appId) {
		Optional<CitizenAppEntity> app = appRepo.findById(appId);
		if(app.isPresent()) {
			DcCaseEntity entity=new DcCaseEntity();
			entity.setAppId(appId);
			dcCaseRepo.save(entity);
			return entity.getCaseNum();
		}
		return 0l;
	}

	@Override
	public Map<Integer, String> getPlanNames() {
		List<PlanEntity> findAll = planRepo.findAll();
		
		Map<Integer,String> plansMap=new HashMap<>();
		for(PlanEntity entity :findAll) {
			plansMap.put(entity.getPlanID(), entity.getPlanName());
		}
		return plansMap;
	}

	@Override
	public Long savePlanSelection(PlanSelection planSelection) {
		Optional<DcCaseEntity> findById = dcCaseRepo.findById(planSelection.getCaseNum());
		if(findById.isPresent()) {
			DcCaseEntity dcCaseEntity = findById.get();
			dcCaseEntity.setPlanId(planSelection.getPlanId());
			dcCaseRepo.save(dcCaseEntity);
			return planSelection.getCaseNum();
		}
		/*
		 * DcCaseEntity entity=new DcCaseEntity();
		 * entity.setPlanId(planSelection.getPlanId());
		 * entity.setAppId(planSelection.getAppId()); entity = dcCaseRepo.save(entity);
		 * if(entity.getCaseNum() !=null) { return entity.getCaseNum(); }
		 */
		return null;
		
	}

	@Override
	public Long saveIncomeData(Income income) {
		DcIncomeEntity entity=new DcIncomeEntity();
		BeanUtils.copyProperties(income, entity);
		incomeRepo.save(entity);
		return income.getCaseNum();
	}

	@Override
	public Long saveEducation(Education education) {
		DcEducationEntity entity=new DcEducationEntity();
		BeanUtils.copyProperties(education, entity);
		eduRepo.save(entity);
		return education.getCaseNum();
				
	}

	@Override
	public Long saveChildren(ChildRequest request) {
		List<Child> childs = request.getChilds();
		Long caseNum = request.getCaseNum();
		for(Child c:childs) {
			DcChildrenEntity entity=new DcChildrenEntity();
			BeanUtils.copyProperties(c, entity);
			entity.setCaseNum(caseNum);
			childRepo.save(entity);
		}
//		childRepo.saveAll(entities);//saving children all at a time
		return request.getCaseNum();
	}

	@Override
	public DcSummary getSummary(Long caseNum) {
		String planName="";
		
		DcIncomeEntity incomeEntity = incomeRepo.findByCaseNum(caseNum);
		DcEducationEntity educationEntity = eduRepo.findByCaseNum(caseNum);
		List<DcChildrenEntity> childsEntity = childRepo.findByCaseNum(caseNum);
		Optional<DcCaseEntity> dcCase = dcCaseRepo.findById(caseNum);
		if(dcCase.isPresent()) {
			Integer planId = dcCase.get().getPlanId();
			Optional<PlanEntity> plan = planRepo.findById(planId);
			if(plan.isPresent()) {
				planName = plan.get().getPlanName();
			}
		}
		//set the data to summary object 
		DcSummary summary=new DcSummary();
		summary.setPlanName(planName);
		
		Income income=new Income();
		BeanUtils.copyProperties(incomeEntity, income);
		summary.setIncome(income);
		
		Education edu=new Education();
		BeanUtils.copyProperties(educationEntity, edu);
		summary.setEducation(edu);
		
		
		List<Child> childs=new ArrayList<>();
		for(DcChildrenEntity entity:childsEntity) {
			Child ch=new Child();
			BeanUtils.copyProperties(entity, ch);
			childs.add(ch);
		}
		summary.setChilds(childs);
		return summary;
	}

}
