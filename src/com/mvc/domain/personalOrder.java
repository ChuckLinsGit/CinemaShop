package com.mvc.domain;

import java.util.Date;

public class personalOrder {
	
	private String city;
	
	private String cinema;
	
	private String name;
	
	private String address;
	
	private String director;
	
	private String actors;
	
	private Date date;
	
	private int price;
	
	private String phone_fk;
	
	private int seat;
	
	private int movie_num_fk;
	
	private int order_num;

	public String getCity() {
		return city;
	}

	public String getCinema() {
		return cinema;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getDirector() {
		return director;
	}

	public String getActors() {
		return actors;
	}

	public Date getDate() {
		return date;
	}

	public int getPrice() {
		return price;
	}

	public String getPhone_fk() {
		return phone_fk;
	}

	public int getSeat() {
		return seat;
	}

	public int getMovie_num_fk() {
		return movie_num_fk;
	}

	public int getOrder_num() {
		return order_num;
	}

	
	public void setCity(String city) {
		this.city = city;
	}

	public void setCinema(String cinema) {
		this.cinema = cinema;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setPhone_fk(String phone_fk) {
		this.phone_fk = phone_fk;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public void setMovie_num_fk(int movie_num_fk) {
		this.movie_num_fk = movie_num_fk;
	}

	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}

	@Override
	public String toString() {
		return "personalOrder [city=" + city + ", cinema=" + cinema + ", name="
				+ name + ", address=" + address + ", director=" + director
				+ ", actors=" + actors + ", date=" + date + ", price=" + price
				+ ", phone_fk=" + phone_fk + ", seat=" + seat
				+ ", movie_num_fk=" + movie_num_fk + ", order_num=" + order_num
				+ "]";
	}

	public personalOrder(String city, String cinema, String name,
			String address, String director, String actors, Date date,
			int price, String phone_fk, int seat, int movie_num_fk,
			int order_num) {
		super();
		this.city = city;
		this.cinema = cinema;
		this.name = name;
		this.address = address;
		this.director = director;
		this.actors = actors;
		this.date = date;
		this.price = price;
		this.phone_fk = phone_fk;
		this.seat = seat;
		this.movie_num_fk = movie_num_fk;
		this.order_num = order_num;
	}

	public personalOrder() {
		super();
	}

	public personalOrder(String city, String cinema, String name,
			String address, String director, String actors, Date date,
			int price, String phone_fk, int movie_num_fk) {
		super();
		this.city = city;
		this.cinema = cinema;
		this.name = name;
		this.address = address;
		this.director = director;
		this.actors = actors;
		this.date = date;
		this.price = price;
		this.phone_fk = phone_fk;
		this.movie_num_fk = movie_num_fk;
		//初始化未0，一边筛选之前下单但未预定座位的订单,更改后依旧出现单号初始化
		this.seat = 0;
		
	}

	
	
}
