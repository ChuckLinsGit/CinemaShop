package com.mvc.domain;

import java.util.Date;

public class comments {
	
	private String comment;
	
	private String publisher;
	
	private Date date;
	
	private int comment_num;
	
	public int getComment_num() {
		return comment_num;
	}

	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}

	private String phone_fk;
	
	private int movie_num_fk;

	public String getComment() {
		return comment;
	}

	public String getPublisher() {
		return publisher;
	}

	public Date getDate() {
		return date;
	}

	public String getPhone_fk() {
		return phone_fk;
	}

	public int getMovie_num_fk() {
		return movie_num_fk;
	}

	
	
	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setPhone_fk(String phone_fk) {
		this.phone_fk = phone_fk;
	}

	public void setMovie_num_fk(int movie_num_fk) {
		this.movie_num_fk = movie_num_fk;
	}

	


	@Override
	public String toString() {
		return "comments [comment=" + comment + ", publisher=" + publisher
				+ ", date=" + date + ", comment_num=" + comment_num
				+ ", phone_fk=" + phone_fk + ", movie_num_fk=" + movie_num_fk
				+ "]";
	}

	
	
	public comments(String comment, String publisher, Date date,
			String phone_fk, int movie_num_fk) {
		super();
		this.comment = comment;
		this.publisher = publisher;
		this.date = date;
		this.phone_fk = phone_fk;
		this.movie_num_fk = movie_num_fk;
	}

	public comments(String comment, String publisher, Date date,
			int comment_num, String phone_fk, int movie_num_fk) {
		super();
		this.comment = comment;
		this.publisher = publisher;
		this.date = date;
		this.comment_num = comment_num;
		this.phone_fk = phone_fk;
		this.movie_num_fk = movie_num_fk;
	}

	public comments() {
		super();
	}

	
	
}
