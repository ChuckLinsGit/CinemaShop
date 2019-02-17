<%@page import="customModule.entity.customer"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/style.css">
	
	<script type="text/javascript">  
    // 刷新图片  
    function changeImg() {  
    	
    	var imgEle=document.getElementById("imgObj")  ;
    	imgEle.src="imageValidate.validate?a="+new Date().getTime(); 
	}
</script> 


  </head>
  
  <body>	
  <p>
  <br>
  <div>
  		用户：<span>
  			<% 
  			customer customer=(customer)session.getAttribute("customer");
  			if(customer==null){
  		%>	
  			会员未登录
  		<%
  			}else{
  		%>
  		<%=customer.getName()%>
  		</span>
  		&nbsp;&nbsp;&nbsp;<a  href="personalData.jsp">个人中心</a>
  		&nbsp;&nbsp;&nbsp;<a href="loginoff.cust">注销用户</a>
  		<%	
  			}
  		%>
 		&nbsp;&nbsp;&nbsp;<a href="index.jsp">返回首页</a>
 </div>
  <br>
  		<%
 			 if(customer==null){
 		%>	 
  <hr></hr>
  <br>
  <p>
	<div>
		<form action="login.cust">
		 手机：<input type="text" name="phone"/><p>
		 密码：<input type="password" name="password"/><p>
		验证码：<input type="text" name="inCode" size="3" />
		<input type="image" id="imgObj" src="imageValidate.validate"/>
		<a href="javascript:changeImg()">换一张</a><br><P>
		<%
			Object msg=request.getAttribute("mdg");
  			if(msg!=null){
  		%>
  			<tr>
  				<td>
  					<P><%=msg %></a></P>
  				</td>
  			</tr>
  		<%
  		}
  		 %>
		<input type="submit"  value="提交"/><p>
		</form>
	</div>
	<%
		}
	 %>
  </body>
</html>
