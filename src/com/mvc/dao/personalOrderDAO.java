package com.mvc.dao;

import java.util.List;




import com.mvc.domain.personalOrder;
import com.mvc.domain.seatTable;

public interface personalOrderDAO {
	
	public List<personalOrder>  getAll();
	
	public List<personalOrder> getPersonalOrder(Integer phone_fk);
	
	public personalOrder getWhileSeatis0(Integer film_num,String cinema);
	
	public personalOrder getPersonalNewOrder(Integer order_num);
	
	public void deletePersonalOrder(Integer phone_fk);
	
	public void deleteTempOrder(Integer order_num);
	
	public void savePrice(personalOrder personalOrder);
	
	public void saveSeats(Integer seat,Integer order_num ); 
	

}
