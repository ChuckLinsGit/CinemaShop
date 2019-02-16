package com.mvc.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class jdbcUtils {
	
	private 2static DataSource dataSource=null;
	
	static{
		dataSource=new ComboPooledDataSource("app");
	}
	
	public static Connection getConnection()throws SQLException {
		return dataSource.getConnection();
	}

	
	public static void releaseConnection(Connection connection) throws SQLException{
		connection.close();
	}
}
