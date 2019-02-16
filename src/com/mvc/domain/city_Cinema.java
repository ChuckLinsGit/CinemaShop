package com.mvc.domain;

public class city_Cinema {

	private int cinema_num;
	
	private String city;
	
	private String cinema;
	
	private String address;
	
	private String buttonChar;

	public int getCinema_num() {
		return cinema_num;
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

	public String getButtonChar() {
		return buttonChar;
	}

	@Override
	public String toString() {
		return "city_Cinema [cinema_num=" + cinema_num + ", city=" + city
				+ ", cinema=" + cinema + ", address=" + address
				+ ", buttonChar=" + buttonChar + "]";
	}

	public city_Cinema(int cinema_num, String city, String cinema,
			String address, String buttonChar) {
		super();
		this.cinema_num = cinema_num;
		this.city = city;
		this.cinema = cinema;
		this.address = address;
		this.buttonChar = buttonChar;
	}

	public city_Cinema() {
	}

	public void setCinema_num(int cinema_num) {
		this.cinema_num = cinema_num;
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

	public void setButtonChar(String buttonChar) {
		this.buttonChar = buttonChar;
	}

	
}
