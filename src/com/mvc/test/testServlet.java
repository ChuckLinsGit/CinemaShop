package com.mvc.test;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class testServlet extends HttpServlet {

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
		//String succeed =String.valueOf(request.getAttribute("succeed111"));
		//System.out.println(succeed);
		
		//System.out.println("test");
		//request.getRequestDispatcher("testMore").forward(request,response);
		
		//String succeed=String.valueOf(request.getParameter("succeedForOrder"));
		//System.out.println(succeed);
		
		String succeed1=String.valueOf(request.getAttribute("succeed1"));
		String succeed2=String.valueOf(request.getAttribute("succeed2"));
		System.out.println("servlet:"+succeed1+succeed2);
		request.getRequestDispatcher("MyTestJSp.jsp").forward(request, response);;
	}
}	
