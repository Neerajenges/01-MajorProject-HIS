package com.eg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class CalculatorTest {
	@Test
	public void add() {
		Calculator c=new Calculator();
		int actualResult = c.add(20, 30);
		int expectedResult=50;
		assertEquals(expectedResult, actualResult);
	}
	@Test
	public void mul() {
		Calculator c=new Calculator();
		int actualResult = c.mul(12, 4);
		int expectedResult=48;
		assertEquals(expectedResult, actualResult);
		
	}

}
