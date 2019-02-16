
package com.mvc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.miaodiyun.httpApiDemo.common.HttpUtil;
import com.miaodiyun.httpApiDemo.IndustrySMS;
import com.mvc.test.sendMessageUtil;
import com.mvc.validateFunctions.validateCode;

public class validateCodeServlet extends HttpServlet {

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
		
		String servletPath=request.getServletPath();
		String methodName=servletPath.substring(1);
		methodName=methodName.substring(0, methodName.length()-9);
		
		//System.out.println(methodName);
		
		try{
			Method method=getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, request,response);
			}catch(Exception e){
				e.printStackTrace();
				response.sendRedirect("error.jsp");
			}
		
		
		
	

	}
	
	/** 
	 * 响应验证码页面 
	 * @return 
	 * @throws IOException 
	 */   
	public void imageValidate(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		try{
			// 设置响应的类型格式为图片格式  
		    response.setContentType("image/jpeg");  
		    //禁止图像缓存。  
		    response.setHeader("Pragma", "no-cache");  
		    response.setHeader("Cache-Control", "no-cache");  
		    response.setDateHeader("Expires", 0);  
		  
		    HttpSession session = request.getSession();  
		  
		    validateCode vCode = new validateCode(120,40,5,100);  
		    session.setAttribute("code", vCode.getCode());  
		    vCode.write(response.getOutputStream());  
			
			}catch(Exception e){
				e.printStackTrace();
				response.sendRedirect("error.jsp");
			}
	}

public void messageValidate(HttpServletRequest request,HttpServletResponse response) throws IOException{
	


	
	String phone=request.getParameter("phoneCopy");
	System.out.println(phone);
	String password=request.getParameter("password");
	String mail=request.getParameter("mail");
	String name=request.getParameter("name");
	
	request.setAttribute("phone", phone);
	request.setAttribute("password", password);
	request.setAttribute("name",name );
	request.setAttribute("mail", mail);
	
	
		try{
		
			if(phone==""){
				request.setAttribute("mdg", "手机号不能为空！");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
		
			String Randomcode=HttpUtil.createRandomVcode();
			System.out.println(Randomcode);
		    HttpSession session = request.getSession();  
		    session.setAttribute("msgCode", Randomcode);  

		    IndustrySMS.to=phone;
		    IndustrySMS.smsContent="【映像影院】验证码："+Randomcode+"，打死都不要告诉别人哦！";
		    //IndustrySMS.execute();
		    
		    request.setAttribute("mdg", "验证码已发送！");
		    request.getRequestDispatcher("register.jsp").forward(request, response);

			}catch(Exception e){
				e.printStackTrace();
				response.sendRedirect("error.jsp");
			}
	}
}
