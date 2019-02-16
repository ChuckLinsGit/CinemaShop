package com.mvc.domain;

public class customer {
	private String phone;
	
	private String password;
	
	private String name;
	
	private String mail;

	public String  getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@Override
	public String toString() {
		return "customer [phone=" + phone + ", password=" + password
				+ ", name=" + name + ", mail=" + mail + "]";
	}

//提供了构造函数了，要有无参和有参的包含这俩属性的。
	public customer(String phone, String password, String name, String mail) {
		super();
		this.phone = phone;
		this.password = password;
		this.name = name;
		this.mail = mail;
	}


	public customer() {
		super();
	}
	
	
}
