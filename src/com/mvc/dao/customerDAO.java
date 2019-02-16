package com.mvc.dao;

import java.util.List;


import com.mvc.domain.customer;

public interface customerDAO {
	
	public List<customer>  getAll();
	
	public void save(customer customer);
	
	public void update(customer customer);
	
	public customer get(String phone);
	
	public void delete (String phone);

	/**
	 * ��½ʱ���ݵ绰������֤
	 * @param name
	 * @return
	 */
	public long getCountWithPhone(String phone);
}
