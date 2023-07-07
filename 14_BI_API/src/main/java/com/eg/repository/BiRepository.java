package com.eg.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eg.binding.ApplicantsDtlsEntity;
import com.eg.entity.BiEntity;

public interface BiRepository extends JpaRepository<BiEntity, Serializable> {
	
	public BiEntity findByStatus(String planStatus);
	

}
