package com.mvc.dao;

import java.util.List;


import com.mvc.domain.wanna_See;



public interface wanna_SeeDAO {

	public List<wanna_See>  getAll();
	
	public List<wanna_See> getByPhone(String phone_fk);
	
	public wanna_See getByMovie_num(Integer movie_num,String phone);
	
	public void delete (Integer moive_num,String phone);
	
	public void save(wanna_See wanna_See);
}
