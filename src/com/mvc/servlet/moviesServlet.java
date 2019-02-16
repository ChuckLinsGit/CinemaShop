package com.mvc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.dao.city_CinemaDAO;
import com.mvc.dao.commentsDAO;
import com.mvc.dao.moviesDAO;
import com.mvc.dao.personalOrderDAO;
import com.mvc.dao.personalRecorderDAO;
import com.mvc.dao.seatTableDAO;
import com.mvc.dao.wanna_SeeDAO;
import com.mvc.domain.city_Cinema;
import com.mvc.domain.movies;
import com.mvc.domain.comments;
import com.mvc.domain.personalOrder;
import com.mvc.domain.personalRecorder;
import com.mvc.domain.seatTable;
import com.mvc.domain.wanna_See;
import com.mvc.impl.city_CinemaDAOJdbcImpl;
import com.mvc.impl.commentsDAOJdbcImpl;
import com.mvc.impl.moviesDAOJdbcImpl;
import com.mvc.impl.personalOrderDAOJdbcImpl;
import com.mvc.impl.personalRecorderDAOJdbcImpl;
import com.mvc.impl.seatTabelDAOJdbcImpl;
import com.mvc.impl.wanna_SeeDAOJdbcImpl;

public class moviesServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	
	private city_CinemaDAO cinemaDAO=new city_CinemaDAOJdbcImpl();
	private moviesDAO moviesDAO=new moviesDAOJdbcImpl();
	private commentsDAO commentsDAO=new commentsDAOJdbcImpl();
	private personalOrderDAO orderDAO=new personalOrderDAOJdbcImpl();
	private personalRecorderDAO recorderDAO=new personalRecorderDAOJdbcImpl();
	private wanna_SeeDAO wanna=new wanna_SeeDAOJdbcImpl();
	private seatTableDAO seatDAO=new seatTabelDAOJdbcImpl();
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String servletPath=request.getServletPath();
		String methodName=servletPath.substring(1);
		methodName=methodName.substring(0, methodName.length()-7);
		System.out.println(methodName);
		
		try{
			Method method=getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, request,response);
			}catch(Exception e){
				e.printStackTrace();
				response.sendRedirect("error.jsp");
			}
	}
	
	//从别的页面转到本页面选购电影需传入movie_num值
	public void getFilm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		String forwardPath="/error.jsp";
		
		String film_numStr=request.getParameter("movie_num");
		int film_num=Integer.parseInt(film_numStr);
		
		try{
			//获取电影信息
			movies film=(movies) moviesDAO.getmovies(film_num);
			List<comments> filmComments= (List<comments>) commentsDAO.getFilmcomments(film_num);
			seatTable seatTableForFilm=(seatTable) seatDAO.getseatTable(film_num);
			System.out.println(seatTableForFilm);
			
			HttpSession session=request.getSession();
			//检验该电影对象是否存在，存在则继续往下操作
	  		if(film!=null){
	  			session.setAttribute("film", film);
	  			session.setAttribute("comments", filmComments);
	  			session.setAttribute("seattable", seatTableForFilm);
	  		}			
			request.getRequestDispatcher("/movies.jsp").forward(request, response);
			
		}catch(NumberFormatException e){
				request.getRequestDispatcher(forwardPath).forward(request, response);
			}
	}
	
	
public void submitPrice(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		String forwardPath="/error.jsp";
		
		String priceStr=request.getParameter("price");
		System.out.println(priceStr);
		int price=Integer.parseInt(priceStr);
		String phone_num=request.getParameter("phone_num");
		String film_numStr=request.getParameter("film_num");
		int film_num=Integer.parseInt(film_numStr);
		
		try{
			
			movies film=(movies) moviesDAO.getmovies(film_num);
			city_Cinema cinema=(city_Cinema)cinemaDAO.getCinema(film.getCinema_num_fk());
			
			//删除之前下单但未预定座位的订单
			String cinema1=cinema.getCinema();
			personalOrder NewOrder=(personalOrder)orderDAO.getWhileSeatis0(film_num, cinema1);
			if(NewOrder!=null&&NewOrder.getSeat()==0){
				orderDAO.deleteTempOrder(NewOrder.getOrder_num());
			}
			//存储order订单信息
			personalOrder personalorder=new personalOrder(cinema.getCity(), cinema.getCinema(), film.getName(), cinema.getAddress(), film.getDirector(), film.getActors(), film.getDate(), price, phone_num, film.getMovie_num());
			System.out.println(personalorder);
			orderDAO.savePrice(personalorder);
			
			//新订单变量
			NewOrder=(personalOrder)orderDAO.getWhileSeatis0(film_num, cinema1);//getWhileSeat0
			System.out.println(NewOrder);

			//获取此次order订单单号，交给seatOrder完成后一起传给personalData.jsp
			int NewOrder_num=NewOrder.getOrder_num();
			System.out.println(NewOrder_num);
			request.setAttribute("NewOrder_num", NewOrder_num);
			//跳转到电影页面
			request.getRequestDispatcher("/movies.jsp").forward(request, response);
			
		}catch(NumberFormatException e){
				request.getRequestDispatcher(forwardPath).forward(request, response);
			}
	}
	


public void seatOrder(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
	String forwardPath="/error.jsp";
	
	String seatStr=request.getParameter("seat");
	int seat=Integer.parseInt(seatStr);
	String film_numStr=request.getParameter("film_num");
	int film_num=Integer.parseInt(film_numStr);
	String NewOrder_numStr=request.getParameter("NewOrder_num");
	int NewOrder_num=Integer.parseInt(NewOrder_numStr);
	
	try{	
		System.out.println(film_num);
		System.out.println(seat);
		int seatIfOrdered=seatDAO.getSeats(seat, film_num);
		System.out.println(seatIfOrdered);
		System.out.println(NewOrder_num);
		
		personalOrder NewOrder=orderDAO.getPersonalNewOrder(NewOrder_num);
		System.out.println(NewOrder);
		//存储此次订单座位信息并跳转到个人信息订单页面
		//检验该订单对象是有存在，存在才据徐往下执行
		if(NewOrder!=null){
			//检验该座位是否已被预定，若被预定，则不用执行操作
			if(seatIfOrdered==0){
			int order_num=NewOrder.getOrder_num();
			System.out.println(order_num);
			orderDAO.saveSeats(seat, order_num);
			seatDAO.ordered(seat);
			
			request.setAttribute("order", NewOrder);
			
			request.getRequestDispatcher("/personalData.jsp").forward(request, response);
			}else {
				request.setAttribute("message", "该座位已被预定");
				request.getRequestDispatcher("/movies.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("message", "请先选择价格！！");
			request.getRequestDispatcher("/movies.jsp").forward(request, response);
		}
		
		
	}catch(NumberFormatException e){
			request.getRequestDispatcher(forwardPath).forward(request, response);
		}
	}

public void submitWanna(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
	String forwardPath="/error.jsp";
	
	String phone_num=request.getParameter("phone_num");
	System.out.println(phone_num);
	String film_numStr=request.getParameter("film_num");
	System.out.println(film_numStr);
	int film_num=Integer.parseInt(film_numStr);
	
	try{
		wanna_See wannaForword=(wanna_See)wanna.getByMovie_num(film_num,phone_num);
		System.out.println(wannaForword);
		//检验该我想看记录是否在存在，不存在则写入数据库，存在则不用执行该操作；
		if(wannaForword==null){
		movies film=(movies) moviesDAO.getmovies(film_num);	
		System.out.println(film);
		wanna_See newWanna=new wanna_See(film.getMovie_num(), film.getName(), film.getDirector(), film.getActors(), film.getDate(), phone_num);
		System.out.println(newWanna);
		wanna.save(newWanna);
		}
		//跳转到电影页面
		response.sendRedirect("movies.jsp");
		
	}catch(NumberFormatException e){
			request.getRequestDispatcher(forwardPath).forward(request, response);
		}
}



public void commentsReply(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, ParseException{
	
	String forwardPath="/error.jsp";
	
	String publisher=new String(String.valueOf(request.getParameter("publisher")).getBytes("ISO8859-1"),"UTF-8");
	System.out.println(publisher);
	String beReplied=new String(String.valueOf(request.getParameter("beReplied")).getBytes("ISO8859-1"),"UTF-8");
	System.out.println(beReplied);
	String phone=request.getParameter("phone_fk");
	
	try{
			//将被回复用户名跟评论加入数据库
			String newComment=new String(String.valueOf(request.getParameter("reply")).getBytes("ISO8859-1"),"UTF-8");
			System.out.println(newComment);
			newComment="回复"+beReplied+":  "+newComment;
			System.out.println(newComment);
			String film_numStr=request.getParameter("movie_num");
			int film_num=Integer.parseInt(film_numStr); 
			comments c1=new comments(newComment, publisher,new Date(),phone, film_num);
			System.out.println(c1);
			commentsDAO.save(c1);	
				
			String url="getFilm.movies?movie_num="+film_num;
			response.sendRedirect(url);
		
	}catch(NumberFormatException e){
			request.getRequestDispatcher(forwardPath).forward(request, response);
		}
	}




public void addComments(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, ParseException{
	
	String forwardPath="/error.jsp";
	
	String publisher=request.getParameter("publisher");
	String phone=request.getParameter("phone_fk");
	
	try{
			//将评论加入数据库
			String newComment=new String(String.valueOf(request.getParameter("comment")).getBytes("ISO8859-1"),"UTF-8");
			String film_numStr=request.getParameter("movie_num");
			int film_num=Integer.parseInt(film_numStr); 
			System.out.println(new Date(new Date().getTime()));
			comments c1=new comments(newComment, publisher,new Date(),phone,film_num);
			System.out.println(c1);
			commentsDAO.save(c1);	
				
			String url="getFilm.movies?movie_num="+film_num;
			response.sendRedirect(url);
		
	}catch(NumberFormatException e){
			request.getRequestDispatcher(forwardPath).forward(request, response);
		}
	}




public void deleteComment(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, ParseException{
	
	String forwardPath="/error.jsp";
	
	String movie_numStr=request.getParameter("movie_num");
	int movie_num=Integer.parseInt(movie_numStr);
	String comment_numStr=request.getParameter("comment_num");
	int comment_num=Integer.parseInt(comment_numStr);
	
	try{
			//将评论删除
			commentsDAO.deletePersonalcomment(comment_num);	
			
			String url="getFilm.movies?movie_num="+movie_num;
			response.sendRedirect(url);
		
	}catch(NumberFormatException e){
			request.getRequestDispatcher(forwardPath).forward(request, response);
		}
	}
		
}



