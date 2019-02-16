package com.mvc.impl;

import java.util.List;

import com.mvc.dao.DAO;
import com.mvc.dao.customerDAO;
import com.mvc.domain.customer;

public class customerDAOJdbcutilImpl extends DAO<customer> implements customerDAO{

	@Override
	public List<customer> getAll() {
		String sql="SElECT * FROM PERSONALDATA";
		return getForList(sql);
	}

	@Override
	public void save(customer customer) {
		String sql="INSERT INTO personaldata VALUE(?,?,?,?)";
		update(sql, customer.getPhone(),customer.getPassword(),customer.getName(),customer.getMail());
	}

	@Override
	public customer get(String phone) {
		
		String sql="SELECT * FROM PERSONALDATA WHERE PHONE=?";
		return  get(sql, phone);
	
	}

	@Override
	public void delete(String phone) {
		String sql="DELETE FROM PERSONALDATA WHERE PHONE=?";
		update(sql, phone);
	}

	@Override
	public long getCountWithPhone(String phone) {
		String sql="SELECT COUNT(*) FROM PERSONALDATA WHERE PHONE=?";
		return getForVlaue(sql, phone);
	}

	@Override
	public void update(customer customer) {
		String sql="UPDATE PERSONALDATA SET PASSWORD=?,NAME=?,MAIL=? WHERE PHONE=?";
		update(sql,customer.getPassword(),customer.getName(),customer.getMail(),customer.getPhone());
	}

}
