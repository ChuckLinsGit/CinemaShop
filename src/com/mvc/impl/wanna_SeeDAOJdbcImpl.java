package com.mvc.impl;

import java.sql.Date;
import java.util.List;





import com.mvc.dao.DAO;
import com.mvc.dao.wanna_SeeDAO;
import com.mvc.domain.wanna_See;

public class wanna_SeeDAOJdbcImpl extends DAO<wanna_See> implements wanna_SeeDAO{

	@Override
	public List<wanna_See> getAll() {
		String sql="SELECT * FROM WANNA_SEE";
		return getForList(sql);
	}

	@Override
	public List<wanna_See> getByPhone(String phone_fk) {
		String sql="SELECT * FROM WANNA_SEE WHERE PHONE_FK=?";
		return getForList(sql, phone_fk);
	}
	
	@Override
	public wanna_See getByMovie_num(Integer movie_num,String phone) {
		String sql="SELECT * FROM WANNA_SEE WHERE MOVIE_NUM_FK=? AND PHONE_FK=?";
		return get(sql, movie_num,phone);
	}

	@Override
	public void delete(Integer movie_num,String phone) {
		String sql="DELETE FROM WANNA_SEE WHERE MOVIE_NUM_FK=? AND PHONE_FK=?";
		update(sql,movie_num,phone);
	}

	@Override
	public void save(wanna_See wanna_See) {
		String sql="INSERT INTO WANNA_SEE VALUE(?,?,?,?,?,?)";
		update(sql,wanna_See.getMovie_num_fk(),wanna_See.getName(),wanna_See.getDirector(),
				wanna_See.getActor(),new Date(wanna_See.getDate().getTime()),wanna_See.getPhone_fk());
	}
}
