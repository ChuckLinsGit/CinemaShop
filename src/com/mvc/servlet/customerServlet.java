package com.mvc.servlet;

import java.io.IOException;


import com.miaodiyun.httpApiDemo.IndustrySMS;
import com.mvc.dao.customerDAO;
import com.mvc.domain.customer;
import com.mvc.domain.movies;
import com.mvc.impl.customerDAOJdbcutilImpl;
import com.mvc.validateFunctions.validateCode;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;


public class customerServlet extends HttpServlet {

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
	
	private customerDAO custDAO=new customerDAOJdbcutilImpl();
	
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
			methodName=methodName.substring(0, methodName.length()-5);
			
			try{
				Method method=getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
				method.invoke(this, request,response);
				}catch(Exception e){
					e.printStackTrace();
					response.sendRedirect("error.jsp");
				}
	}
	public void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
			String forwardPath="error.jsp";
		
			String phone=request.getParameter("phone");
			String password=request.getParameter("password");
			String inCode=request.getParameter("inCode");
			String code=String.valueOf(request.getSession().getAttribute("code"));
			
			
			System.out.println(code);
			System.out.println(inCode);
			System.out.println(inCode.equals(code));
			
			if(phone==""){
				request.setAttribute("mdg", "手机号不能为空！");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			
			if(inCode.equals(code)){

				System.out.println("验证码正确！！");
				try{
				
					System.out.println(phone);
					long custCount=custDAO.getCountWithPhone(phone);
					System.out.println(custCount);
					if(custCount<=0){
						request.setAttribute("mdg", "该用户不存在！");
						request.getRequestDispatcher("login.jsp").forward(request, response);
						return;
					}else{
						System.out.println(phone);
						customer custLogin=(customer)custDAO.get(phone);
						System.out.println(custLogin.toString());
						if(password.equals(custLogin.getPassword())){
							
							//将customer设为会话值，可以被其他页面引用
							HttpSession session=request.getSession();
							session.setAttribute("customer", custLogin);
					
							request.getRequestDispatcher("personalData.jsp").forward(request, response);
							return;
						}else{
							request.setAttribute("mdg", "密码错误！");
							request.getRequestDispatcher("login.jsp").forward(request, response);
							return;
						}
					}
				}catch(NumberFormatException e){
					request.getRequestDispatcher(forwardPath).forward(request, response);
				}
			}else{
				
				request.setAttribute("mdg", "验证码错误！");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
				return;			
			}
	}
	

	public void register(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		String forwardPath="error.jsp";
	
		String phone=request.getParameter("phone");
		String password=request.getParameter("password");
		String mail=request.getParameter("mail");
		String name=new String(String.valueOf(request.getParameter("name")).getBytes("ISO8859-1"),"UTF-8");
		String msgCode=request.getParameter("messageCode");
		String code=String.valueOf(request.getSession().getAttribute("msgCode"));

		
		System.out.println(code);
		System.out.println(msgCode);
		System.out.println(msgCode.equals(code));
		
		if(phone==""){
			request.setAttribute("mdg", "手机号不能为空！");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		
		if(msgCode.equals(code)){

			System.out.println("验证码正确！！");
			try{		
				Long custCount=custDAO.getCountWithPhone(phone);
				if(custCount>0){
					request.setAttribute("mdg", "该用户已存在！");
					request.getRequestDispatcher("register.jsp").forward(request, response);
					return;
				}else{
					customer custRegister=new customer(phone, password, name, mail);
					
					custDAO.save(custRegister);
					
					HttpSession session=request.getSession();
					session.setAttribute("customer", custRegister);
					
					request.setAttribute("mdg", "注册成功！");
					request.getRequestDispatcher("personalData.jsp").forward(request, response);
				}
			}catch(NumberFormatException e){
				request.getRequestDispatcher(forwardPath).forward(request, response);
			}
		}else{
			
			request.setAttribute("mdg", "验证码错误！");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;			
		}
}
	
	public void loginoff(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session=request.getSession();
		session.setAttribute("customer", null);
		
		String url="login.jsp";
		url=new String(url.getBytes("UTF-8"),"ISO8859-1");
		response.sendRedirect(url);
	}
}