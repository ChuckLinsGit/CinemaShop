<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/style.css">
	
	<script type="text/javascript">
		function exchange()
	{
		 var value=document.getElementById("phoneId").value;
    	document.getElementById("phoneCopyId").value=value;
    	
    	var value=document.getElementById("passwordId").value;
    	document.getElementById("passwordCopyId").value=value;
    	
    	var value=document.getElementById("nameId").value;
    	document.getElementById("nameCopyId").value=value;
    	
    	var value=document.getElementById("mailId").value;
    	document.getElementById("mailCopyId").value=value;
	}
	</script>
	
  </head>
  
  <body>
  <br>
 会员注册&nbsp;&nbsp;&nbsp;<a href="index.jsp">返回首页</a>
 <p>
 <hr></hr>
  <br>
  <br>
  <p>
	<div class="d_2">
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
		<form action="register.cust">
		 手机：<input type="text" name="phone" id="phoneId" ><p>
		 验证码：<input type="text" name="messageCode" size="3"/><p>
		 密码：<input type="password" name="password" id="passwordId"><p>
		 昵称：<input type="text" name="name" id="nameId"><p>
		 邮箱：<input type="text" name="mail" id="mailId"><p>
		<input type="submit" style="margin-left=200px" value="提交"/>
		</form>
		
		 <form action="messageValidate.validate">
		 <input type="hidden" name="password"  id="passwordCopyId"/>
		 <input type="hidden" name="name"  id="nameCopyId"/>
		 <input type="hidden" name="mail" id="mailCopyId"/>
		 <input type="hidden" name="phoneCopy" id="phoneCopyId" >
		 	 <input type="submit" value="手机短信验证" onclick="exchange()">
		 </form>

	</div>
  </body>
</html>
