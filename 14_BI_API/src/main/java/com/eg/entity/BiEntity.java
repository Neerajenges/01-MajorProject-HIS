package com.eg.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="BENEFIT_ISSUANCE")
@Data
public class BiEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer biId;
	private Long caseNum;
	private String fullName;
	private Long ssn;
	private Double benefitAmt;
	private String bankName;
	private Long accNo;
	
}
