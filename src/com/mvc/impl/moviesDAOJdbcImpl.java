package com.mvc.impl;

import java.util.List;



import com.mvc.dao.DAO;
import com.mvc.dao.moviesDAO;
import com.mvc.domain.movies;
import com.mvc.domain.personalOrder;

public class moviesDAOJdbcImpl extends DAO<movies> implements moviesDAO {


	@Override
	public movies getmovies(Integer movie_num_fk) {
		
		String sql="SELECT * FROM MOVIES WHERE MOVIE_NUM=?";
		return get(sql, movie_num_fk);
	}

	@Override
	public void delete(Integer movie_num_fk) {
		
		String sql="DELETE FROM MOVIES WHERE MOVIE_NUM=?";
		update(sql, movie_num_fk);
	}

	@Override
	public List<movies> getCinema_movies(Integer cinema_num_fk) {
		String sql="SELECT * FROM MOVIES WHERE CINEMA_NUM_FK=?";
		return getForList(sql, cinema_num_fk);
	}

}
