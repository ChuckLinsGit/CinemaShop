package com.mvc.dao;

import java.util.List;



import com.mvc.domain.personalRecorder;

public interface personalRecorderDAO {
	
	public List<personalRecorder>  getAll();
	
	public List<personalRecorder> get(String  phone_fk);
	
	public void delete (Integer phone_fk);
	
	public void save(personalRecorder personalRecorder);
	

}
