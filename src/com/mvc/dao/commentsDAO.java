package com.mvc.dao;

import java.util.List;

import com.mvc.domain.comments;
import com.mvc.domain.customer;

public interface commentsDAO {

	public List<comments>  getAll();
	
	public List<comments> getFilmcomments(Integer movie_num);
	
	public List<comments> getPersonalcomments(String phone_fk);
	
	public void deletePersonalcomment(Integer comment_num);
	
	public void save (comments comments);
	
}
