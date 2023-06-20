package com.eg.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="DC_CASEs")
@Data
public class DcCaseEntity {
	@Id
	@GeneratedValue
	private Long caseNum;
	private Integer appId;
	private Integer planId;
	
	

}
