package com.eg.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eg.entity.DcEducationEntity;

public interface DcEducationRepo extends JpaRepository<DcEducationEntity, Serializable>{
	public DcEducationEntity findByCaseNum(Long caseNum);
	

}
