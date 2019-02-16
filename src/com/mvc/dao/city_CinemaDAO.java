package com.mvc.dao;

import java.util.List;

import com.mvc.domain.city_Cinema;

public interface city_CinemaDAO {

	public List<city_Cinema> getAll();
	
	public List<city_Cinema> getCinemas(String city);
	
	public city_Cinema getCinema(Integer cinema_num);

}
