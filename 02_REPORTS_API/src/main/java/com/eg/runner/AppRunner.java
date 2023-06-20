package com.eg.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.eg.entity.EligibilityDetails;
import com.eg.repo.EligibilityDetailsRepo;

@Component
public class AppRunner implements ApplicationRunner{
//in spring boot ApplicationRunner is used to store dummy data to database ,it is stored at run time
	@Autowired
	private EligibilityDetailsRepo repo;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		 EligibilityDetails entity1=new EligibilityDetails();
		 entity1.setEligId(1);
		 entity1.setName("Neeraj");
		 entity1.setMobile(1234569l);
		 entity1.setPlanStatus("Approved");
		 entity1.setPlanName("SNAP");
		 entity1.setGender('M');
		 entity1.setSsn(787687613l);
		 repo.save(entity1);
		 
		 EligibilityDetails entity2=new EligibilityDetails();
		 entity2.setEligId(2);
		 entity2.setName("Alok");
		 entity2.setMobile(123234789l);
		 entity2.setPlanStatus("Denied");
		 entity2.setPlanName("CCAP");
		 entity2.setGender('M');
		 entity2.setSsn(787634523l);
		 repo.save(entity2);
		 
		 EligibilityDetails entity3=new EligibilityDetails();
		 entity3.setEligId(3);
		 entity3.setName("Nishant");
		 entity3.setMobile(123266789l);
		 entity3.setPlanStatus("Closed");
		 entity3.setPlanName("Mediaid");
		 entity3.setGender('M');
		 entity3.setSsn(787635523l);
		 repo.save(entity3);
		 
	}

}
