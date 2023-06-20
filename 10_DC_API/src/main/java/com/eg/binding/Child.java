package com.eg.binding;

import java.time.LocalDate;

import lombok.Data;
@Data
public class Child {
	private Integer childId;
	private LocalDate childDob;
	private Long childSsn;
	private Long caseNum;
	

}
