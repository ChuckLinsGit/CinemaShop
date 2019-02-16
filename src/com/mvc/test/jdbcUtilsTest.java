package com.mvc.test;

import java.sql.Connection;

import java.sql.SQLException;

import org.junit.Test;

import com.mvc.db.jdbcUtils;

public class jdbcUtilsTest {

	@Test
	public void testGetConnection() throws SQLException{
		Connection connection=jdbcUtils.getConnection();
		System.out.println(connection);
	}

}
