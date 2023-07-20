package com.eg.service.test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.powermock.api.mockito.PowerMockito;

import com.eg.dao.UserDao;
import com.eg.service.UserService;

public class UserServiceTest {
	
		@Test
		public void getNameTest() {
		// creating mock object
		UserDao userDaoMock = PowerMockito.mock (UserDao.class);
		// defining mock obj behaviour
		PowerMockito.when
		(userDaoMock.findNameById(101)).thenReturn("Neeraj");
		// injecting mock obj into service obj
		UserService service = new UserService (userDaoMock);
		String actualName = service.getName(101);
		String expectedName = "Neeraj";
		assertEquals (expectedName, actualName);
		}
}
