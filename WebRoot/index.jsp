<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="customModule.entity.customer"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
首页<p>
用户<% 
  			customer customer=(customer)session.getAttribute("customer");
  			if(customer==null){
  		%>	
  			会员未登录	<p>
  			&nbsp;&nbsp;&nbsp;<a href="login.jsp">会员登录</a>&nbsp;&nbsp;&nbsp;<a href="register.jsp">注册会员</a><p>
			&nbsp;&nbsp;&nbsp;<a href="city_Cinema.jsp">选购电影</a><p>  		
  		<%
  			}else{
  			
  		%>
  			<%=customer.getName() %>
	&nbsp;&nbsp;&nbsp;<a href="personalData.jsp?phoneForDataPhone=<%=customer.getPhone()%>">个人中心</a><p>
	&nbsp;&nbsp;&nbsp;<a href="loginoff.cust">注销用户</a>
  		<% 	
  			}
  		%>
	
  </body>
</html>
