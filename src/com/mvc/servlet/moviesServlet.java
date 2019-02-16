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
	
	//�ӱ��ҳ��ת����ҳ��ѡ����Ӱ�贫��movie_numֵ
	public void getFilm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		String forwardPath="/error.jsp";
		
		String film_numStr=request.getParameter("movie_num");
		int film_num=Integer.parseInt(film_numStr);
		
		try{
			//��ȡ��Ӱ��Ϣ
			movies film=(movies) moviesDAO.getmovies(film_num);
			List<comments> filmComments= (List<comments>) commentsDAO.getFilmcomments(film_num);
			seatTable seatTableForFilm=(seatTable) seatDAO.getseatTable(film_num);
			System.out.println(seatTableForFilm);
			
			HttpSession session=request.getSession();
			//����õ�Ӱ�����Ƿ���ڣ�������������²���
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
			
			//ɾ��֮ǰ�µ���δԤ����λ�Ķ���
			String cinema1=cinema.getCinema();
			personalOrder NewOrder=(personalOrder)orderDAO.getWhileSeatis0(film_num, cinema1);
			if(NewOrder!=null&&NewOrder.getSeat()==0){
				orderDAO.deleteTempOrder(NewOrder.getOrder_num());
			}
			//�洢order������Ϣ
			personalOrder personalorder=new personalOrder(cinema.getCity(), cinema.getCinema(), film.getName(), cinema.getAddress(), film.getDirector(), film.getActors(), film.getDate(), price, phone_num, film.getMovie_num());
			System.out.println(personalorder);
			orderDAO.savePrice(personalorder);
			
			//�¶�������
			NewOrder=(personalOrder)orderDAO.getWhileSeatis0(film_num, cinema1);//getWhileSeat0
			System.out.println(NewOrder);

			//��ȡ�˴�order�������ţ�����seatOrder��ɺ�һ�𴫸�personalData.jsp
			int NewOrder_num=NewOrder.getOrder_num();
			System.out.println(NewOrder_num);
			request.setAttribute("NewOrder_num", NewOrder_num);
			//��ת����Ӱҳ��
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
		//�洢�˴ζ�����λ��Ϣ����ת��������Ϣ����ҳ��
		//����ö����������д��ڣ����ڲž�������ִ��
		if(NewOrder!=null){
			//�������λ�Ƿ��ѱ�Ԥ��������Ԥ��������ִ�в���
			if(seatIfOrdered==0){
			int order_num=NewOrder.getOrder_num();
			System.out.println(order_num);
			orderDAO.saveSeats(seat, order_num);
			seatDAO.ordered(seat);
			
			request.setAttribute("order", NewOrder);
			
			request.getRequestDispatcher("/personalData.jsp").forward(request, response);
			}else {
				request.setAttribute("message", "����λ�ѱ�Ԥ��");
				request.getRequestDispatcher("/movies.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("message", "����ѡ��۸񣡣�");
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
		//��������뿴��¼�Ƿ��ڴ��ڣ���������д�����ݿ⣬��������ִ�иò�����
		if(wannaForword==null){
		movies film=(movies) moviesDAO.getmovies(film_num);	
		System.out.println(film);
		wanna_See newWanna=new wanna_See(film.getMovie_num(), film.getName(), film.getDirector(), film.getActors(), film.getDate(), phone_num);
		System.out.println(newWanna);
		wanna.save(newWanna);
		}
		//��ת����Ӱҳ��
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
			//�����ظ��û��������ۼ������ݿ�
			String newComment=new String(String.valueOf(request.getParameter("reply")).getBytes("ISO8859-1"),"UTF-8");
			System.out.println(newComment);
			newComment="�ظ�"+beReplied+":  "+newComment;
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
			//�����ۼ������ݿ�
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
			//������ɾ��
			commentsDAO.deletePersonalcomment(comment_num);	
			
			String url="getFilm.movies?movie_num="+movie_num;
			response.sendRedirect(url);
		
	}catch(NumberFormatException e){
			request.getRequestDispatcher(forwardPath).forward(request, response);
		}
	}
		
}



