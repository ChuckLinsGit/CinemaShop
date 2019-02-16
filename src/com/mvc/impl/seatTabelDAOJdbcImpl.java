package com.mvc.impl;

import com.mvc.dao.DAO;
import com.mvc.dao.seatTableDAO;
import com.mvc.domain.seatTable;

public class seatTabelDAOJdbcImpl extends DAO<seatTable>implements seatTableDAO{

	@Override
	public int getSeats(Integer seat, Integer movie_num) {
		String sql="SELECT (SEAT_"+seat+") FROM SEATTABLE WHERE  MOVIE_NUM_FK=?";
		return getForVlaue(sql,movie_num);
		
	}

	@Override
	public void delete(Integer movie_num_fk) {
		String sql="DELETE FROM SEATTABLE WHERE MOVIE_NUM_FK=?";
		update(sql, movie_num_fk);
	}

	@Override
	public void ordered(int seat) {
		String sql="UPDATE SEATTABLE SET  SEAT_"+seat+"="+seat;
		update(sql);
	}

	@Override
	public void canceled(int seat) {
		String sql="UPDATE SEATTABLE SET SEAT_"+seat+"=0";
		update(sql);
	}

	@Override
	public seatTable getseatTable(Integer movie_num) {
		String sql="SELECT * FROM SEATTABLE WHERE  MOVIE_NUM_FK=?";
		return get(sql,movie_num);
	}

}
