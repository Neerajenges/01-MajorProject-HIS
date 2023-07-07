package com.eg;

import java.io.File;
import java.io.FileReader;

public class TryWithResources {
	public static void main(String[] args) {
		try(FileReader fr=new FileReader(new File("abc.txt"))){
			int read = fr.read();
			System.out.println(read);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
