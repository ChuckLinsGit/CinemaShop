package com.mvc.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.dao.commentsDAO;
import com.mvc.dao.customerDAO;
import com.mvc.dao.personalOrderDAO;
import com.mvc.dao.personalRecorderDAO;
import com.mvc.dao.wanna_SeeDAO;
import com.mvc.domain.comments;
import com.mvc.domain.customer;
import com.mvc.domain.personalOrder;
import com.mvc.domain.personalRecorder;
import com.mvc.domain.wanna_See;
import com.mvc.impl.commentsDAOJdbcImpl;
import com.mvc.impl.customerDAOJdbcutilImpl;
import com.mvc.impl.personalOrderDAOJdbcImpl;
import com.mvc.impl.personalRecorderDAOJdbcImpl;
import com.mvc.impl.wanna_SeeDAOJdbcImpl;

public class personalDataServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private commentsDAO commentsDAO=new commentsDAOJdbcImpl();
	private customerDAO Me=new customerDAOJdbcutilImpl();
	private personalOrderDAO newOrder=new personalOrderDAOJdbcImpl();
	private personalRecorderDAO myRecorder=new personalRecorderDAOJdbcImpl();
	private wanna_SeeDAO myWanna=new wanna_SeeDAOJdbcImpl();
	private commentsDAO myComments=new commentsDAOJdbcImpl();
	

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
//多个请求对应一个servlet
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			String servletPath=request.getServletPath();
			String methodName=servletPath.substring(1);
			methodName=methodName.substring(0, methodName.length()-5);
			
			//System.out.println(methodName);
			
			try{
				Method method=getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
				method.invoke(this, request,response);
				}catch(Exception e){
					e.printStackTrace();
					response.sendRedirect("error.jsp");
				}
	}

//其他页面跳转到个人信息页面需要传入phone值
	public void dataPage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		String forwardPath="/error.jsp";
		String phone=String.valueOf(request.getAttribute("phoneForDataPage"));
		System.out.println(phone);

		
		try{
			
			List<personalRecorder> r=myRecorder.get(phone);
			List<comments> c=myComments.getPersonalcomments(phone);
			List<wanna_See> w=myWanna.getByPhone(phone);
			
			System.out.println(r);
			System.out.println(c);
			System.out.println(w);
			
			HttpSession session=request.getSession();
			
			session.setAttribute("recorders", r);
			session.setAttribute("comments", c);
			session.setAttribute("wanna", w);
			request.setAttribute("phoneForDataPage",phone);
			
			request.getRequestDispatcher("/personalData.jsp").forward(request, response);
		
		}catch(NumberFormatException e){
			request.getRequestDispatcher(forwardPath).forward(request, response);
		}

	}
	
//修改个人信息
public void updateData(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
	String forwardPath="/error.jsp";

	String phone=request.getParameter("phoneForUpdate");
	System.out.println(phone);
	String newName=new String(String.valueOf(request.getParameter("name")).getBytes("ISO8859-1"),"UTF-8");
	String newMail=request.getParameter("mail");
	String newPassword=request.getParameter("password");

	try{
		
			customer newMe=new customer(phone, newPassword, newName, newMail);
			
			Me.update(newMe);
			HttpSession session=request.getSession();
			session.setAttribute("customer", newMe);
			request.getRequestDispatcher("/personalData.jsp").forward(request, response);;
	
	}catch(NumberFormatException e){
		request.getRequestDispatcher(forwardPath).forward(request, response);
	}
}

//跳转支付宝支付页面；

public void turnToAlipay(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

	String forwardPath="/error.jsp";

	String order_numStr=request.getParameter("order_num");
	int order_num=Integer.parseInt(order_numStr);

	try{
		
			personalOrder thisOrder=(personalOrder) newOrder.getPersonalNewOrder(order_num);
				
			String url="Alipay.jsp?"
					+ "&order_num="+thisOrder.getOrder_num()
					+"&phoneForSubmit="+thisOrder.getPhone_fk()
					+ "&price="+thisOrder.getPrice()
					+ "&name="+URLEncoder.encode(thisOrder.getName(),"UTF-8");
			url=new String(url.getBytes("UTF-8"),"ISO8859-1");
			response.sendRedirect(url);
			
			
	}catch(NumberFormatException e){
		request.getRequestDispatcher(forwardPath).forward(request, response);
	}

}


//将订单信息存如记录数据表中
public void submitOrder(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
	String forwardPath="/error.jsp";
	String succeed=String.valueOf(request.getParameter("succeedForOrder"));
	String phoneForSubmit=String.valueOf(request.getParameter("phoneForSubmit"));
	String order_numStr=String.valueOf(request.getParameter("order_num"));
	int order_num=Integer.parseInt(order_numStr);

	try{
			if(succeed!=null&&succeed.equals("true")){
			personalOrder thisOrder=(personalOrder) newOrder.getPersonalNewOrder(order_num);
			System.out.println(thisOrder);
			personalRecorder newRecorder=new personalRecorder(thisOrder.getCity(), thisOrder.getCinema(), thisOrder.getAddress(), thisOrder.getName(), thisOrder.getDirector(), thisOrder.getActors(), thisOrder.getDate(), thisOrder.getSeat(), thisOrder.getOrder_num(), thisOrder.getPrice(), thisOrder.getPhone_fk());
			
			myRecorder.save(newRecorder);
			//更新记录
			List<personalRecorder> r=myRecorder.get(phoneForSubmit);
			request.setAttribute("recorders", r);
			request.getRequestDispatcher("/personalData.jsp").forward(request, response);
			}else{
				request.setAttribute("message", "付款失败！");
				request.getRequestDispatcher("/Alipay.jsp").forward(request, response);
			}
	}catch(NumberFormatException e){
		request.getRequestDispatcher(forwardPath).forward(request, response);
	}
}

public void deleteComment(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, ParseException{
	
	String forwardPath="/error.jsp";
	
	String phone=String.valueOf(request.getParameter("phoneForDataPage"));
	System.out.println(phone);
	String comment_numStr=request.getParameter("comment_num");
	int comment_num=Integer.parseInt(comment_numStr);
	
	try{
			//将评论删除
			commentsDAO.deletePersonalcomment(comment_num);	
			
			request.setAttribute("phoneForDataPage", phone);
			request.getRequestDispatcher("dataPage.data").forward(request, response);
		
	}catch(NumberFormatException e){
			request.getRequestDispatcher(forwardPath).forward(request, response);
		}
	}

public void deleteWanna(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
	String forwardPath="/error.jsp";
	
	String phoneForDataPage=request.getParameter("phoneForDataPage");
	String film_numStr=request.getParameter("film_num");
	
	int film_num=Integer.parseInt(film_numStr);
	
	System.out.println(phoneForDataPage);
	System.out.println(film_num);
	
	wanna_See wannaForword=(wanna_See)myWanna.getByMovie_num(film_num,phoneForDataPage);
	System.out.println(wannaForword);
	try{
		//检验该我想看记录是否存在，若存在则删除，不存在则不用执行操作
		System.out.println(wannaForword!=null);
		if(wannaForword!=null){
			//无法执行删除操作！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
			myWanna.delete(film_num, phoneForDataPage);
		}
		//跳转到电影页面
		request.setAttribute("phoneForDataPage", phoneForDataPage);
		request.getRequestDispatcher("dataPage.data").forward(request, response);
		
	}catch(NumberFormatException e){
			request.getRequestDispatcher(forwardPath).forward(request, response);
		}
}

}
