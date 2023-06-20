package com.eg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eg.entity.DummyData;

public interface DummyDataRepo extends JpaRepository<DummyData, Integer>{
	
	DummyData getCitizenBySsn(Long ssn);
	

}
