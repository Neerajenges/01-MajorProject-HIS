package com.eg.userDefined;

public class Demo {
	public static void main(String[] args) throws Exception {
		Demo d=new Demo();
		d.getData(101);
	
		
	}
	public void getData(Integer id) throws Exception {
		if(id<=100) {
			System.out.println("getting data from db");
		}else {
			throw new NoDataFoundException("Invalid Id");
		}
	}

}
