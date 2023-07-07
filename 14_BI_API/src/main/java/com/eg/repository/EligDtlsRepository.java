package com.eg.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eg.entity.EligDtlsEntity;

public interface EligDtlsRepository extends JpaRepository<EligDtlsEntity, Serializable>{
	
	//public EligDtlsEntity findByCaseNum(Long caseNum);
	//public EligDtlsEntity findByPlanStatus(String planStatus);
	public List<EligDtlsEntity> findByPlanStatus(String planStatus);

}
