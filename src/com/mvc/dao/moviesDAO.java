package com.mvc.dao;

import java.util.List;



import com.mvc.domain.movies;
import com.mvc.domain.personalOrder;

public interface moviesDAO {
	
	public movies getmovies(Integer movie_num_fk);
	
	public List<movies> getCinema_movies(Integer cinema_num_fk);
	
	public void delete (Integer movie_num_fk);
	
}
