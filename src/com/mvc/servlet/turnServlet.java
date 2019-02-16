package com.mvc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class turnServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		
		String url="submitOrder.data?succeedForOrder=true"
				+"&phoneForSubmit="+String.valueOf(request.getParameter("phoneForSubmit"))
				+"&order_num="+String.valueOf(request.getParameter("order_num"));
		
		System.out.println("inpUt"+String.valueOf(request.getParameter("phoneForSubmit")));
		System.out.println(String.valueOf("inpUt"+request.getParameter("order_num")));
		url=new String(url.getBytes("UTF-8"),"ISO8859-1");
		response.sendRedirect(url);
	
		/*
		String url="http://localhost:8080/cinemaProject/test?succeedForOrder=true";
		url=new String(url.getBytes("UTF-8"),"ISO8859-1");
		response.sendRedirect(url);
		*/
		
		
		/*
		String url="http://localhost:8080/cinemaProject/test";
		//url=new String(url.getBytes("UTF-8"),"ISO8859-1");
		String succeed="true";
		request.setAttribute("succeedForOrder", succeed);
		request.getRequestDispatcher(url).forward(request, response);
		*/
	}

}
