package com.mvc.dao;

import java.util.List;



import com.mvc.domain.seatTable;


public interface seatTableDAO {
	
	public int getSeats(Integer seat, Integer movie_num);
	
	public seatTable getseatTable(Integer movie_num);
	
	public void delete (Integer movie_num_fk);
	
	public void ordered (int seat);
	
	public void canceled (int seat);
}
