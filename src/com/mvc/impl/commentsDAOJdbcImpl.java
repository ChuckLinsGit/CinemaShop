package com.mvc.impl;

import java.sql.Date;
import java.util.List;



import com.mvc.dao.DAO;
import com.mvc.dao.commentsDAO;
import com.mvc.domain.comments;

public class commentsDAOJdbcImpl extends DAO<comments>implements commentsDAO{

	@Override
	public List<comments> getAll() {
		String sql="SELECT * FROM COMMENTS";
		return getForList(sql);
	}
	
	public List<comments> getFilmcomments(Integer film_num) {
		String sql="SELECT * FROM COMMENTS WHERE MOVIE_NUM_FK=?";
		return 	getForList(sql, film_num);
	}

	@Override
	public List<comments> getPersonalcomments(String phone_fk) {
		String sql="SELECT * FROM COMMENTS WHERE PHONE_FK=?";
		return 	getForList(sql, phone_fk);
	}

	@Override
	public void deletePersonalcomment(Integer comment_num) {
		String sql="DELETE FROM COMMENTS WHERE COMMENT_NUM=?";
		update(sql, comment_num);
	}

	@Override
	public void save(comments comments) {
		String sql="INSERT INTO COMMENTS (COMMENT,PUBLISHER,DATE,PHONE_FK,MOVIE_NUM_FK) VALUE(?,?,?,?,?)";
		update(sql, comments.getComment(),comments.getPublisher(),
					new Date(comments.getDate().getTime()),comments.getPhone_fk(),
					comments.getMovie_num_fk());
	}

}
