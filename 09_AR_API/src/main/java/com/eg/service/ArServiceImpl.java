package com.eg.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.eg.binding.CitizenApp;
import com.eg.dummy.DummyData;
import com.eg.entity.CitizenAppEntity;
import com.eg.repository.CitizenAppRepository;

@Service
public class ArServiceImpl implements ArService{
	
	@Autowired
	private CitizenAppRepository appRepo;

	@Override
	public Integer createApplication(CitizenApp app) {
		//make rest call to ssa-web api with ssn as input
		String endpointUrl="localhost:9095/getCitizen/{ssn}";
		Long ssn = app.getSsn();
		
		/*
		 * // restTemplate approach RestTemplate rt=new RestTemplate();
		 * ResponseEntity<String> resEntity =
		 * rt.getForEntity(endpointUrl,String.class,app.getSsn()); String stateName =
		 * resEntity.getBody();
		 */
		
//		factory method to create webClient instance ,webClient is an interface 
		WebClient webClient=WebClient.create();
		
		
		DummyData dummyData = webClient.get()	//it represent get request
				 .uri(endpointUrl, ssn)		//it represent url to send req
				 .retrieve()				//to retrieve response
				 .bodyToMono(DummyData.class)	//to specify response type
				 .block();					//to make synchronous call
//		String stateName = webClient.get()	//it represent get request
//				 .uri(endpointUrl, ssn)		//it represent url to send req
//				 .retrieve()				//to retrieve response
//				 .bodyToMono(String.class)	//to specify response type
//				 .block();					//to make synchronous call
		
		System.out.println("*****");
		
		if("New Jersey".equals(dummyData.getStateName())) {
			//create application
			CitizenAppEntity entity=new CitizenAppEntity();
			BeanUtils.copyProperties(app, entity);
			entity.setStateName(dummyData.getStateName());
			CitizenAppEntity save = appRepo.save(entity);
			
			return save.getAppId();
		}
		
		return 0;
	}

}
