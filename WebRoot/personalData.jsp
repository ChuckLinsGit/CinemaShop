<%@page import="cinemaModule.entity.comments"%>
<%@page import="customModule.entity.wanna_See"%>
<%@page import="customModule.entity.personalRecorder"%>
<%@page import="cinemaModule.entity.movies"%>
<%@page import="customModule.entity.personalOrder"%>
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
    
    <title>My JSP 'personalData.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/style.css">


  </head>
  <!--       其他页面跳转到跟页面都需附带信息            -->
  <body>
  <br>
个人信息
&nbsp;&nbsp;&nbsp;<a href="index.jsp">返回首页</a>
&nbsp;&nbsp;&nbsp;<a href="city_Cinema.jsp">选购电影</a>
&nbsp;&nbsp;&nbsp;<a href="loginoff.cust">注销用户</a><p>

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
  			
  		<% 
  			
  			customer customer=(customer)session.getAttribute("customer");
  			if(customer==null){
  		%>	
  			会员未登录,<a href="login.jsp">请前往登陆页面</a>
  		<%
  			}else{
  				if(request.getAttribute("phoneForDataPage")==null){
  				request.setAttribute("phoneForDataPage", customer.getPhone());
  				//从movies页面来的订单信息以request.Attribute信息存储，转发后再转发回来是否会消失？
  				request.getRequestDispatcher("dataPage.data").forward(request, response);
  				}else{
	
	%>
  		 <br>
  <hr></hr>
  <br>
  <div class="d_1">
  	基本信息
  	<form action="updateData.data">
  		 手机：<input type="text" name="phone" disabled="disabled" value="<%=customer.getPhone() %>"/><p>
  		 	 <input type="hidden" name="phoneForUpdate"  value="<%=customer.getPhone() %>"/>
		 密码：<input type="password" name="password" value="<%=customer.getPassword() %>"/><p>
		 昵称：<input type="text" name="name" value="<%=customer.getName() %>"/><p>
		 邮箱：<input type="text" name="mail" value="<%=customer.getMail() %>"/><p>
		 <input type="submit" style="margin-left=200px" value="提交修改"/><p>
  	</form>
  </div>
  <br>
  <hr></hr>
  <br>
 	 <div class="d_1">
 	 	<p>订单</p>
 	 	<table class="tb_1" cellspacing=0>
 	 		<tr>
 	 		<th class="t_1">影名</th>
 	 		<th class="t_1">价格</th>
 	 		<th class="t_1">时间</th>
 	 		<th class="t_1"></th>
 	 		</tr>
 	 		<%
 	 			//批量提交订单用数组？
  				personalOrder myOrder=(personalOrder)request.getAttribute("order");
  				if(myOrder!=null){
 	 			
 	 		%>
 	 		<tr>
 	 			<td class="t_2"><%=myOrder.getName()%></td>
 	 			<td	class="t_2"><%=myOrder.getPrice() %></td>
 	 			<td class="t_2"><%=myOrder.getDate() %></td>
 	 			<!-- 跳转支付宝支付后再存储 -->
 	 			<td class="t_2">
 	 			<a href="turnToAlipay.data?order_num=<%=myOrder.getOrder_num()%>">提交订单</a></td>
 	 		</tr>
 	 		<%
 	 		}
 	 		 %>
 	 	</table>
 	 </div>
  <br>
  <hr></hr>
  <br>
 	 <div class="d_1">
 	 	<p>观影记录</p>
 	 		<%

  					List<personalRecorder> recorders=(List<personalRecorder>)session.getAttribute("recorders");
 	 			
 	 		%>
 	 	<table class="tb_1" cellspacing=0>
 	 		<tr>
 	 		<th class="t_1">影院</th>
 	 		<th class="t_1">影名</th>
 	 		<th class="t_1">价格</th>
 	 		<th class="t_1">时间</th>
 	 		</tr>
 	 		<%
 	 			if(recorders!=null&&recorders.size()>0){
 	 				for(personalRecorder personalrecorder:recorders){
 	 		%>
 	 		<tr>
 	 		<td class="t_2"><%=personalrecorder.getCinema() %></td>
 	 		<td class="t_2"><%=personalrecorder.getName() %></td>
 	 		<td class="t_2"><%=personalrecorder.getPrice() %></td>
 	 		<td class="t_2"><%=personalrecorder.getDate() %></td>
 	 		</tr>
 	 		<% 		
 	 				}
 	 			}
 	 		 %>
 	 	</table>
 	 </div>
  <br>
  <hr></hr>
  <br>
 	 <div class="d_1">
 	 	<p>我想看</p>
 	 	<%
  					List<wanna_See> wanna=(List<wanna_See>)session.getAttribute("wanna");	
 	 	%>
 	 	<table class="tb_1" cellspacing=0>
 	 		<tr>
 	 		<th class="t_1">影名</th>
 	 		<th class="t_1">导演</th>
 	 		<th class="t_1">演员</th>
 	 		<th class="t_1">时间</th>
 	 		<th class="t_1"></th>
 	 		<th class="t_1"></th>
 	 		</tr>
 	 		<%
 	 			if(wanna!=null&&wanna.size()>0){
 	 				for(wanna_See wanna_see:wanna){
 	 		%>
 	 		<tr>
 	 		<td class="t_2"><%=wanna_see.getName() %></td>
 	 		<td class="t_2"><%=wanna_see.getDirector()%></td>
 	 		<td ><%=wanna_see.getActor()%></td>
 	 		<td class="t_2"><%=wanna_see.getDate()%></td>
 	 		<td class="t_2"><a href="getFilm.movies?movie_num=<%=wanna_see.getMovie_num_fk()%>">前往购买</a></td>
 	 		<td class="t_2"><a href="deleteWanna.data?film_num=<%=wanna_see.getMovie_num_fk()%>&phoneForDataPage=<%=wanna_see.getPhone_fk()%>">删除</a></td>
 	 		</tr>
 	 		<% 		
 	 				}
 	 			}
 	 		 %>
 	 	</table>
 	 </div> 
  <br>
  <hr></hr>
  <br>
  <div class="d_1">
  <p>我的影评</p>
  		<%
  			List<Comment> myComments=(List<Comment>)session.getAttribute("comments");
  		%>
  	<table class="tb_1" cellspacing=0px >
  	<tr>
  		<th class="text_3">评论</th>
  		<th class="text_2">时间戳</th>
  		<th class="text_2">删除</th>
  		<%
  			if(myComments!=null&&myComments.size()>0){
  		 	 				for(Comment c:myComments){
  		%>
 	 		<tr>
 	 		<td class="t_2"><%=c.getComment() %></td>
 	 		<td class="t_2"><%=c.getDate()%></td>
 	 		<td class="t_2"><td class="text_2"><a href="deleteComment.data?comment_num=<%=c.getComment_num()%>&phoneForDataPage=<%=customer.getPhone()%>">删除评论</a></td>
 	 		</tr>
 	 		<% 		
 	 				}
 	 			}
 	 		}
 	 	}
 	 		 %>
  	</table>
  </div>
  <p>
  <p>
  <hr></hr>
  <p>
  </body>
</html>
