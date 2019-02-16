package com.mvc.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mvc.dao.customerDAO;
import com.mvc.domain.customer;
import com.mvc.impl.customerDAOJdbcutilImpl;

public class customerDAOJdbcutilImplTest {

	private customerDAO	customerdao=new customerDAOJdbcutilImpl();
	@Test
	public void testGetAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetInteger() {
	//	customer cust=customerdao.get(1);
	//	System.out.println(cust);
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCountWithPhone() {
		fail("Not yet implemented");
	}

}
