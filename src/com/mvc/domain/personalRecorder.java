package com.mvc.domain;

import java.util.Date;

public class personalRecorder {
	
	private String city;
	
	private String cinema;
	
	private String address;
	
	private String name;
	
	private String director;
	
	private String actors;
	
	private Date date;
	
	private int seat;
	
	private int order_num_fk;
	
	private int price;
	
	private String phone_num_fk;

	
	public String getPhone_num_fk() {
		return phone_num_fk;
	}

	public void setPhone_num_fk(String phone_num_fk) {
		this.phone_num_fk = phone_num_fk;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCity() {
		return city;
	}

	public String getCinema() {
		return cinema;
	}

	public String getAddress() {
		return address;
	}

	public String getName() {
		return name;
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

	public int getSeat() {
		return seat;
	}

	public int getOrder_num_fk() {
		return order_num_fk;
	}
	
	

	public void setCity(String city) {
		this.city = city;
	}

	public void setCinema(String cinema) {
		this.cinema = cinema;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public void setOrder_num_fk(int order_num_fk) {
		this.order_num_fk = order_num_fk;
	}

	@Override
	public String toString() {
		return "personalRecorder [city=" + city + ", cinema=" + cinema
				+ ", address=" + address + ", name=" + name + ", director="
				+ director + ", actors=" + actors + ", date=" + date
				+ ", seat=" + seat + ", order_num_fk=" + order_num_fk + "]";
	}

	public personalRecorder(String city, String cinema, String address,
			String name, String director, String actors, Date date, int seat,
			int order_num_fk, int price, String phone_num_fk) {
		super();
		this.city = city;
		this.cinema = cinema;
		this.address = address;
		this.name = name;
		this.director = director;
		this.actors = actors;
		this.date = date;
		this.seat = seat;
		this.order_num_fk = order_num_fk;
		this.price = price;
		this.phone_num_fk = phone_num_fk;
	}

	public personalRecorder() {
	}


	
	
}
