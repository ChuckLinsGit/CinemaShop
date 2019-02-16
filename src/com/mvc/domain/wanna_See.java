package com.mvc.domain;

import java.util.Date;

public class wanna_See {
	
	private int movie_num_fk;
	
	private String name;
	
	private String director;
	
	private String actor;
	
	private Date date;
	
	private String phone_fk;

	public int getMovie_num_fk() {
		return movie_num_fk;
	}

	public String getName() {
		return name;
	}

	public String getDirector() {
		return director;
	}

	public String getActor() {
		return actor;
	}

	public Date getDate() {
		return date;
	}

	public String getPhone_fk() {
		return phone_fk;
	}

	public void setMovie_num_fk(int movie_num_fk) {
		this.movie_num_fk = movie_num_fk;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setPhone_fk(String phone_fk) {
		this.phone_fk = phone_fk;
	}

	@Override
	public String toString() {
		return "wanna_See [movie_num_fk=" + movie_num_fk + ", name=" + name
				+ ", director=" + director + ", actor=" + actor + ", date="
				+ date + ", phone_fk=" + phone_fk + "]";
	}

	public wanna_See(int movie_num_fk, String name, String director,
			String actor, Date date, String phone_fk) {
		super();
		this.movie_num_fk = movie_num_fk;
		this.name = name;
		this.director = director;
		this.actor = actor;
		this.date = date;
		this.phone_fk = phone_fk;
	}

	public wanna_See() {
		super();
	}
	
	

}
