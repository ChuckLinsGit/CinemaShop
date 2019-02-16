package com.mvc.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.dao.city_CinemaDAO;
import com.mvc.dao.moviesDAO;
import com.mvc.domain.city_Cinema;
import com.mvc.domain.movies;
import com.mvc.impl.city_CinemaDAOJdbcImpl;
import com.mvc.impl.moviesDAOJdbcImpl;

public class city_cinemaServlet extends HttpServlet {

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
	
		private city_CinemaDAO city_CinemaDAO=new city_CinemaDAOJdbcImpl();
		private moviesDAO cinema_movies=new moviesDAOJdbcImpl();
	
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
			
			//System.out.println(methodName);
			
			try{
				Method method=getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
				method.invoke(this, request,response);
				}catch(Exception e){
					e.printStackTrace();
					response.sendRedirect("error.jsp");
				}
			}
	
	public void getCinemas(HttpServletRequest request,HttpServletResponse response) 
								throws ServletException, IOException{
		
		String forwardPath="/error.jsp";
		
		String locatMethod=request.getParameter("method");
		String city=null;
		
		try{
			
			if(locatMethod.equals("handle")){
				
				//city=request.getParameter("city");
				city=new String(request.getParameter("city").getBytes("ISO8859-1"),"UTF-8");
				System.out.println(city);
			}
			if(locatMethod.equals("auto")){
				 System.out.println("auto");
				//获取ip地址
				String ip = request.getHeader("x-forwarded-for");
		        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		            ip = request.getHeader("Proxy-Client-IP");
		        }
		        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		            ip = request.getHeader("WL-Proxy-Client-IP");
		        }
		        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		            ip = request.getRemoteAddr();
		        }
		        
		        //获取物理地址
		        try {
		            //URL U = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=114.111.166.72");
		            URL U = new URL("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
		            URLConnection connection = U.openConnection();  
		            connection.connect();
		            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));  
		            String result = "";
		            String line;
		            while ((line = in.readLine())!= null){  
		                result += line;  
		            }
		            in.close(); 
		            JSONObject jsonObject = JSONObject.fromObject(result);  
		            Map<String, Object> map = (Map) jsonObject;
		            String code = String.valueOf(map.get("code"));//0：成功，1：失败。
		            System.out.println(code);
		            if("1".equals(code)){//失败
		                String data = String.valueOf(map.get("data"));//错误信息
		                request.setAttribute("mdg", "定位失败！请手动定位！");
		                request.getRequestDispatcher("city_Cinema.jsp").forward(request, response);
		                return;
		            }else if("0".equals(code)){//成功
		                Map<String, Object> data = (Map<String, Object>) map.get("data");
		                city= String.valueOf(data.get("region"));//市（县）
		                System.out.println(city);
		            }
		        } catch (MalformedURLException e1) {
		            e1.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        } 
				
			}
			
			List<city_Cinema> cinemas=city_CinemaDAO.getCinemas(city);
			System.out.println(cinemas);
			
			HttpSession session=request.getSession();
	  		session.setAttribute("cinemas", null);
	  		if(cinemas!=null&&cinemas.size()>0){
	  			session.setAttribute("cinemas", cinemas);
	  		}	
	  				
		request.getRequestDispatcher("city_Cinema.jsp").forward(request, response);
				
		}catch(NumberFormatException e){
			request.getRequestDispatcher(forwardPath).forward(request, response);
		}
		
	}
	
	public void getCinema(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		String forwardPath="/error.jsp";
		
		String cinema=URLDecoder.decode(request.getParameter("cinema"), "UTF-8");
		cinema=new String(cinema.getBytes("ISO8859-1"),"UTF-8");
		String cinema_numStr=request.getParameter("cinema_num");
		int cinema_num=Integer.parseInt(cinema_numStr);
		System.out.println(cinema_num);
		
		try{
			List<movies> movies=(List<movies>) cinema_movies.getCinema_movies(cinema_num);
			System.out.println(movies);
			System.out.println(cinema);
			
			HttpSession session=request.getSession();
	  		session.setAttribute("movies", null);
	  		if(movies!=null&&movies.size()>0){
	  			session.setAttribute("movies", movies);
	  		}
			session.setAttribute("cinema", cinema);
			
			request.getRequestDispatcher("city_Cinema.jsp").forward(request, response);
			
		}catch(NumberFormatException e){
				request.getRequestDispatcher(forwardPath).forward(request, response);
			}
	}

}
