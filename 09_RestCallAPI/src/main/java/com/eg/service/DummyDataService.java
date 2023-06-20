package com.eg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eg.entity.DummyData;
import com.eg.repo.DummyDataRepo;
@Service
public class DummyDataService {

	@Autowired
	private DummyDataRepo repo;

	public DummyData getCitizenBySsn(Long ssn) {
		return repo.getCitizenBySsn(ssn);

	}

}
