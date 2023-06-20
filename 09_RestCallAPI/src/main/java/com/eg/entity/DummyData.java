package com.eg.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="Dummy_Data")
@Data
public class DummyData {
	@Id
	private Integer appId;
	private String fullName;
	private String email;
	private Long phno;
	private Long ssn;
	private String gender;
	private String stateName;
	
	

}
