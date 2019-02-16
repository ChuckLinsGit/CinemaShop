package com.mvc.dao;

import java.lang.reflect.ParameterizedType;


import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mvc.db.jdbcUtils;



public class DAO<T> {
	
	private QueryRunner queryRunner=new QueryRunner();
	
	private Class<T> clazz;
	
	
	public DAO() {
		Type superClass=getClass().getGenericSuperclass();
		
		if(superClass instanceof ParameterizedType){
			ParameterizedType parameterizedType=(ParameterizedType) superClass;
			
			Type[] typeArgs=parameterizedType.getActualTypeArguments();
			if(typeArgs!=null&&typeArgs.length>0){
				if(typeArgs[0] instanceof Class){
					clazz=(Class<T>)typeArgs[0];
				} 
			}
		}
	}
	
	/* 以下方法封装查询的各种方法*/
	public <E> E getForVlaue(String sql,Object...args) throws SQLException{
		Connection connection=null;
		
		try{
			connection=jdbcUtils.getConnection();
			return (E) queryRunner.query(connection, sql,new ScalarHandler(),args);
			
		}catch(Exception e){
			
		}finally{
			jdbcUtils.releaseConnection(connection);
		}
		return null;
	}
	
	public List<T> getForList(String sql,Object...args) throws SQLException{
		
		Connection connection=null;
		
		try{
			connection=jdbcUtils.getConnection();
			return queryRunner.query(connection, sql,new BeanListHandler<>(clazz),args);
			
		}catch(Exception e){
			
		}finally{
			jdbcUtils.releaseConnection(connection);
		}
		return null;
	}
	
	public T get(String sql,Object...args) throws SQLException{
		Connection connection=null;
		
		try{
			connection=jdbcUtils.getConnection();
			return  queryRunner.query(connection, sql,new BeanHandler<>(clazz),args);
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			jdbcUtils.releaseConnection(connection);
		}
		return null;	
	}
	
	
	/**
	 * 封装增删改方法
	 * @param sql
	 * @param args
	 * @throws SQLException 
	 */
	public void update(String sql,Object...args) throws SQLException{
		Connection connection=null;
		
		try{
			connection=jdbcUtils.getConnection();
			queryRunner.update(connection,sql,args);
			
		}catch(Exception e){
			
		}finally{
			jdbcUtils.releaseConnection(connection);
		}
	}
	
}
