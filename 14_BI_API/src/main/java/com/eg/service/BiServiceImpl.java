package com.eg.service;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eg.binding.CoResponse;
import com.eg.entity.BiEntity;
import com.eg.entity.CitizenAppEntity;
import com.eg.entity.CoTriggerEntity;
import com.eg.entity.DcCaseEntity;
import com.eg.entity.EligDtlsEntity;
import com.eg.repository.CitizenAppRepository;
import com.eg.repository.CoTriggerRepository;
import com.eg.repository.DcCaseRepo;
import com.eg.repository.EligDtlsRepository;
import com.opencsv.CSVWriter;

@Service
public class BiServiceImpl implements BiService {
//we are using batch processing for that 
	@Autowired
	private CoTriggerRepository coTrgRepo;
	@Autowired
	private EligDtlsRepository eligRepo;
	@Autowired
	private DcCaseRepo dcCaseRepo;
	@Autowired
	private CitizenAppRepository appRepo;

	@Override
	public void createCsv() {

		List<EligDtlsEntity> status = eligRepo.findByPlanStatus("completed");
		List<BiEntity> biEntity = new ArrayList<>();
		for (EligDtlsEntity entity : status) {
			BiEntity biCitizen = new BiEntity();
			BeanUtils.copyProperties(entity, biCitizen);
			biEntity.add(biCitizen);
		}
	

	String path = "E:\\spring\\csv.txt";
	File file = new File(path);
	
	
		try {

			FileWriter opFile = new FileWriter(file);
			CSVWriter writer=new CSVWriter(opFile);
			List<String[]> data=new ArrayList<String []>();
			data.add(new String [] {"caseNum","fullNmae","ssn","benefitAmt","bankName","accNo"});
			for(BiEntity entity:biEntity) {
			//	data.add(new String [] {String.valueOf(entity.getCaseNum(),String.valueOf(entity.getFullName()),entity.getSsn(),entity.getBenefitAmt(),entity.getBankName(),entity.getAccNo())});
				data.add(new String[]{
                        String.valueOf(entity.getCaseNum()),
                        String.valueOf(entity.getFullName()),
                        String.valueOf(entity.getSsn()),
                        String.valueOf(entity.getBenefitAmt()),
                        entity.getBankName(),
                        String.valueOf(entity.getAccNo())
                });
			}
			writer.writeAll(data);
			opFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
}
}


	


