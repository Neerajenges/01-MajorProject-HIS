package com.eg;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PalindromeCheckTest {
	
	@ParameterizedTest()
	@ValueSource(strings= {"racecar","liril","madam","Neeraj"})
	public void isPalindromeTest(String str) {
		//creating target object
		PalindromeCheck pc=new PalindromeCheck();
		//calling target class method
		boolean actual=pc.isPalindrome(str);
		//verifying result
		assertTrue(actual);
	}

}
