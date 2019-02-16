package com.mvc.impl;

import java.sql.SQLException;
import java.util.List;



import com.mvc.dao.DAO;
import com.mvc.dao.city_CinemaDAO;
import com.mvc.domain.city_Cinema;

public class city_CinemaDAOJdbcImpl extends DAO<city_Cinema> implements city_CinemaDAO{

	@Override
	public List<city_Cinema> getAll() throws SQLException {
		String sql="SELECT * FROM CITY_CINEMA";
		return getForList(sql);
	}

	@Override
	public List<city_Cinema> getCinemas(String city) {
		String sql="SELECT * FROM CITY_CINEMA WHERE CITY=?";
		return getForList(sql, city);
	}

	@Override
	public city_Cinema getCinema(Integer cinema_num) {
		String sql="SELECT * FROM CITY_CINEMA WHERE CINEMA_NUM=?";
		return get(sql, cinema_num);
	}

}
