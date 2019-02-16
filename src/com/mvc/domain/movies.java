package com.mvc.domain;

import java.util.Date;

public class movies {
	
	private int movie_num;
	
	private String name;
	
	private String actors;
	
	private String director;
	
	private String brief_Introduction;
	
	private Date date;
	
	private String buttonChar;
	
	private int cinema_num_fk;
	
	private int price2D;
	
	private int price3D;

	public int getMovie_num() {
		return movie_num;
	}

	public String getName() {
		return name;
	}

	public String getActors() {
		return actors;
	}

	public String getDirector() {
		return director;
	}

	public String getBrief_Introduction() {
		return brief_Introduction;
	}

	public Date getDate() {
		return date;
	}

	public String getButtonChar() {
		return buttonChar;
	}

	public int getCinema_num_fk() {
		return cinema_num_fk;
	}

	public int getPrice2D() {
		return price2D;
	}
	
	public int getPrice3D() {
		return price3D;
	}

	@Override
	public String toString() {
		return "movies [movie_num=" + movie_num + ", name=" + name
				+ ", actors=" + actors + ", director=" + director
				+ ", brief_Introduction=" + brief_Introduction + ", date="
				+ date + ", buttonChar=" + buttonChar + ", cinema_num_fk="
				+ cinema_num_fk + ", price2D=" + price2D + ", price3D="
				+ price3D + "]";
	}

	public void setMovie_num(int movie_num) {
		this.movie_num = movie_num;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setBrief_Introduction(String brief_Introduction) {
		this.brief_Introduction = brief_Introduction;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setButtonChar(String buttonChar) {
		this.buttonChar = buttonChar;
	}

	public void setCinema_num_fk(int cinema_num_fk) {
		this.cinema_num_fk = cinema_num_fk;
	}

	public void setPrice2D(int price2d) {
		price2D = price2d;
	}

	public void setPrice3D(int price3d) {
		price3D = price3d;
	}

	public movies(int movie_num, String name, String actors, String director,
			String brief_Introduction, Date date, String buttonChar,
			int cinema_num_fk, int price2d, int price3d) {
		super();
		this.movie_num = movie_num;
		this.name = name;
		this.actors = actors;
		this.director = director;
		this.brief_Introduction = brief_Introduction;
		this.date = date;
		this.buttonChar = buttonChar;
		this.cinema_num_fk = cinema_num_fk;
		price2D = price2d;
		price3D = price3d;
	}

	public movies() {
	}

	
}
