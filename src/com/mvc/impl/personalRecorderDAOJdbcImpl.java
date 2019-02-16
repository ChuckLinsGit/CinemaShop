package com.mvc.impl;

import java.sql.Date;
import java.util.List;







import com.mvc.dao.DAO;
import com.mvc.dao.personalRecorderDAO;
import com.mvc.domain.personalOrder;
import com.mvc.domain.personalRecorder;

public class personalRecorderDAOJdbcImpl extends DAO<personalRecorder> implements personalRecorderDAO{

	@Override
	public List<personalRecorder> getAll() {
		String sql="SELECT * FROM PERSONALRECORDER";
		return getForList(sql);
	}

	@Override
	public List<personalRecorder> get(String  phone_fk) {
		String sql="SELECT * FROM PERSONALRECORDER WHERE PHONE_FK=?";
		return getForList(sql, phone_fk);
	}

	@Override
	public void delete(Integer phone_fk) {
		String sql="DELETE FROM PERSONALRECORDER WHERE PHONE_FK=?";
		update(sql, phone_fk);
	}

	@Override
	public void save(personalRecorder personalRecorder) {
		String sql="INSERT INTO PERSONALRECORDER  VALUE(?,?,?,?,?,?,?,?,?,?,?)";
		update(sql, personalRecorder.getCity(),personalRecorder.getCinema(),personalRecorder.getAddress(),
				personalRecorder.getName(),personalRecorder.getDirector(),personalRecorder.getActors(),
				new Date(personalRecorder.getDate().getTime()),personalRecorder.getSeat(),personalRecorder.getOrder_num_fk(),
				personalRecorder.getPrice(),personalRecorder.getPhone_num_fk());
		
	}



}
