package com.mvc.impl;

import java.util.List;

import com.mvc.dao.DAO;
import com.mvc.dao.personalOrderDAO;
import com.mvc.domain.personalOrder;
import com.mvc.domain.seatTable;

public class personalOrderDAOJdbcImpl extends DAO<personalOrder>implements personalOrderDAO {

	@Override
	public List<personalOrder> getAll() {
		String sql="SELECT * FROM PERSONALORDER";
		return getForList(sql);
	}

	@Override
	public List<personalOrder> getPersonalOrder(Integer phone_fk) {
		String sql="SELECT * FROM PERSONALORDER WHERE PHONE_FK=?";
		return getForList(sql, phone_fk);
	}

	@Override
	public void deletePersonalOrder(Integer phone_fk) {
		
		String sql="DELETE FROM PERSONALORDER WHERE PHONE_FK=?";
		update(sql, phone_fk);
	}
	
	@Override
	public void deleteTempOrder(Integer order_num) {
		
		String sql="DELETE FROM PERSONALORDER WHERE ORDER_NUM=?";
		update(sql, order_num);
	}

	@Override
	public void savePrice(personalOrder personalOrder) {
		//Ω´seat…Ë÷√Œ™0£ª
		String sql="INSERT INTO PERSONALORDER (CITY,CINEMA,ADDRESS,NAME,DIRECTOR,ACTORS,DATE,PRICE,PHONE_FK,MOVIE_NUM_FK,SEAT)"
				+ " VALUE(?,?,?,?,?,?,?,?,?,?,?)";
		update(sql, personalOrder.getCity(),personalOrder.getCinema(),personalOrder.getAddress(),
					personalOrder.getName(),personalOrder.getDirector(),personalOrder.getActors(),
					personalOrder.getDate(),personalOrder.getPrice(),
					personalOrder.getPhone_fk(),personalOrder.getMovie_num_fk(),0);
	}

	@Override
	public void saveSeats(Integer seat,Integer order_num) {
		String sql="UPDATE PERSONALORDER SET SEAT=? WHERE ORDER_NUM=?";
		update(sql,seat,order_num);
		
	}

	@Override
	public personalOrder getWhileSeatis0(Integer film_num, String cinema) {
		String sql="SELECT * FROM PERSONALORDER WHERE MOVIE_NUM_FK=? AND CINEMA=? AND SEAT=0";
		return get(sql, film_num,cinema);
		
	}


	@Override
	public personalOrder getPersonalNewOrder(Integer order_num) {
		String sql="SELECT * FROM PERSONALORDER WHERE ORDER_NUM=?";
		return get(sql, order_num);
	}
	
		

}
