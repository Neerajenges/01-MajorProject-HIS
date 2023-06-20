package com.eg.runner;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.eg.entity.DummyData;
import com.eg.repo.DummyDataRepo;
@Component
public class DummyAppRunner implements ApplicationRunner {
	@Autowired
	private DummyDataRepo repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		repo.deleteAll();
		DummyData dummyData1=new DummyData();
		dummyData1.setAppId(1);
		dummyData1.setFullName("Neeraj");
		dummyData1.setEmail("neeraj@eg.com");
		dummyData1.setPhno(979865438l);
		dummyData1.setSsn(65321l);
		dummyData1.setStateName("New Jersey");
		dummyData1.setGender("M");
		repo.save(dummyData1);
		
		
		DummyData dummyData2=new DummyData();
		dummyData2.setAppId(2);
		dummyData2.setFullName("Alok");
		dummyData2.setEmail("Alok@eg.com");
		dummyData2.setPhno(979865438l);
		dummyData2.setSsn(65322l);
		dummyData2.setStateName("New Jersey");
		dummyData2.setGender("M");
		repo.save(dummyData2);
		
		
		DummyData dummyData3=new DummyData();
		dummyData3.setAppId(3);
		dummyData3.setFullName("Nishant");
		dummyData3.setEmail("Nishant@eg.com");
		dummyData3.setPhno(979865438l);
		dummyData3.setSsn(65323l);
		dummyData3.setStateName("New Jersey");
		dummyData3.setGender("M");
		repo.save(dummyData3);
		
		
		DummyData dummyData4=new DummyData();
		dummyData4.setAppId(4);
		dummyData4.setFullName("Shubham");
		dummyData4.setEmail("Shubham@eg.com");
		dummyData4.setPhno(979865438l);
		dummyData4.setSsn(45321l);
		dummyData4.setStateName("California");
		dummyData4.setGender("M");
		repo.save(dummyData4);
		
		
		DummyData dummyData5=new DummyData();
		dummyData5.setAppId(5);
		dummyData5.setFullName("Elon");
		dummyData5.setEmail("Elon@eg.com");
		dummyData5.setPhno(979865438l);
		dummyData5.setSsn(35321l);
		dummyData5.setStateName("Hawai Island");
		dummyData5.setGender("M");
		repo.save(dummyData5);
		
		
		DummyData dummyData6=new DummyData();
		dummyData6.setAppId(6);
		dummyData6.setFullName("Alina");
		dummyData6.setEmail("Alina@eg.com");
		dummyData6.setPhno(979865438l);
		dummyData6.setSsn(75321l);
		dummyData6.setStateName("Altanta");
		dummyData6.setGender("F");
		repo.save(dummyData6);
		
		
		DummyData dummyData7=new DummyData();
		dummyData7.setAppId(7);
		dummyData7.setFullName("Maria");
		dummyData7.setEmail("Maria@eg.com");
		dummyData7.setPhno(979865438l);
		dummyData7.setSsn(85321l);
		dummyData7.setStateName("New york");
		dummyData7.setGender("F");
		repo.save(dummyData7);
		
	
		
	}

}
