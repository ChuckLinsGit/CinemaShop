
<%@page import="cinemaModule.entity.seatTable"%>
<%@page import="cinemaModule.entity.comments"%>
<%@page import="cinemaModule.entity.movies"%>
<%@page import="customModule.entity.customer"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'movies.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/style.css">


  </head>
  
  <body>
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
  			<%=customer.getName() %>
  		</span>
  		&nbsp;&nbsp;<a  href="personalData.jsp?phoneForDataPhone=<%=customer.getPhone()%>">个人中心</a>
  		&nbsp;&nbsp;&nbsp;<a href="index.jsp">返回首页</a>
  		&nbsp;&nbsp;&nbsp;<a href="loginoff.cust">注销用户</a>
  		<% 	
  			}
  		%>
  		

  </div>
  <br>
  <hr></hr>
  <br>
  <div class="d_2">
  	<%
  		Movie film=(Movie)session.getAttribute("film");
  	  	  		if(film!=null){
  	%>
  	影名：<%=film.getName() %><p>
  	导演：<%=film.getDirector() %><p>
  	演员：<%=film.getActors() %><p>
  	简介：<%=film.getBrief_Introduction() %><p>
  	价格：<table>
  			<% 
  				//登陆后才可提交订单
  				if(customer!=null){
  			%>
  			<tr>
  			<td>
  				<!-- 提交订单 -->
  				<form action="submitPrice.movies?">
  				<input type="hidden" name="price" value=<%=film.getPrice2D() %>>
  				<input type="hidden" name="film_num" value=<%=film.getMovie_num()%>>
  				<input type="hidden" name="phone_num" value=<%=customer.getPhone()%>>
  				<input type="submit" value="2D:" ><%=film.getPrice2D() %>
  				</form>
  			</td>
  			<td>
  				<form action="submitPrice.movies?">
  				<input type="hidden" name="price" value=<%=film.getPrice3D() %>>
  				<input type="hidden" name="film_num" value=<%=film.getMovie_num()%>>
  				<input type="hidden" name="phone_num" value=<%=customer.getPhone()%>>
  				<input type="submit" value="3D:" ><%=film.getPrice3D() %>
  				</form>
  			</td>
  			<td>
  			<!-- 标记我想看 -->
  				<form action="submitWanna.movies?">
  				<input type="hidden" name="film_num" value=<%=film.getMovie_num() %>>
  				<input type="hidden" name="phone_num" value=<%=customer.getPhone() %>>
  				<input type="submit" value="我想看" >
  				</form>
   			</td>
  			<td><span>选择价格后请前往选择座位</span></td>
  			</tr>
  			<%
  				}else{
  			 %>
  			 <tr>
  				<td>
  					<P>未登录，请前往<a href="login.jsp">登陆页面</a></P>
  				</td>
  			</tr>
  			<%
  			}
  			 %>
  		</table>
  	<% 
  	}else{
  	%>
  	<p>未选择电影&nbsp;&nbsp;<a href="city_Cinema.jsp">返回选择电影</a></p>
  	<% 
  	}
  	%>
  		
  </div>
  <br>
  <hr></hr>
  <br>
  <div class="d_1">
  <p>座位预定：</p><!-- 需要添加已被预订的座位弹 出消息 -->
  <table class="tb_1" cellspacing=0>
  	<% 	
  	//登陆后才可预定座位
  		if(customer==null){
  	%>
  	
  	 		<tr>
  				<td>
  					<P>未登录，请前往<a href="login.jsp">登陆页面</a></P>
  				</td>
  			</tr>
	<% 
  	 	} 
  		if(customer!=null){
			Object msg=request.getAttribute("message");
  			if(msg!=null){
  		%>
  			<tr>
  				<td>
  					<P><%=msg %></a></P>
  				</td>
  			</tr>
  		<%
  		}
  			//返回信息包括：未提交订单  座位已被预定
  			//seatTable seatTableForFilm=(seatTable)session.getAttribute("seattable");
  	%>
  		<tr>
  		<th class="t_3"></th>
  		<th class="t_3">col 1</th>
  		<th class="t_3">col 2</th>
  		<th class="t_3">col 3</th>
  		<th class="t_3">col 4</th>
  		<th class="t_3">col 5</th>
  		<th class="t_3">col 6</th>
  		<th class="t_3">col 7</th>
  		<th class="t_3">col 8</th>
  		<th class="t_3">col 9</th>
  		<th class="t_3">col 10</th>
  	</tr>
  	
  	<%
  		for(int i=0;i<5;i++){
  	%>	
  	<!--下面为失败的座位显示预定的代码 -->
  		<tr>
  		<td class="t_3">row <%=i+1 %></td>
  		<td class="t_3">
  		<%--
  			if(seatTableForFilm.seatArray[1+10*i]==0){
  		--%>
  		<a href="seatOrder.movies?seat=<%=1+10*i %>&film_num=<%=film.getMovie_num()%>&cinema_num=<%=film.getCinema_num_fk() %>&NewOrder_num=<%=request.getAttribute("NewOrder_num")%>"><%=1+10*i %></a>
  		<%--	
  			}else{
  			  		该座位已被预定
  		--%>
  		<%--	
  			}
  		 --%>
  		</td>
  		<td class="t_3">	
  		<a href="seatOrder.movies?seat=<%=2+10*i %>&film_num=<%=film.getMovie_num()%>&cinema_num=<%=film.getCinema_num_fk() %>&NewOrder_num=<%=request.getAttribute("NewOrder_num")%>"><%=2+10*i %></a> 
  		</td>
  		<td class="t_3">
  		<a href="seatOrder.movies?seat=<%=3+10*i %>&film_num=<%=film.getMovie_num()%>&cinema_num=<%=film.getCinema_num_fk() %>&NewOrder_num=<%=request.getAttribute("NewOrder_num")%>"><%=3+10*i %></a>  			
  		</td>
  		<td class="t_3">
  		<a href="seatOrder.movies?seat=<%=4+10*i %>&film_num=<%=film.getMovie_num()%>&cinema_num=<%=film.getCinema_num_fk() %>&NewOrder_num=<%=request.getAttribute("NewOrder_num")%>"><%=4+10*i %></a>  		
  		</td>
  		<td class="t_3">
  		<a href="seatOrder.movies?seat=<%=5+10*i %>&film_num=<%=film.getMovie_num()%>&cinema_num=<%=film.getCinema_num_fk() %>&NewOrder_num=<%=request.getAttribute("NewOrder_num")%>"><%=5+10*i %></a>  			
  		</td>
  		<td class="t_3">
  		<a href="seatOrder.movies?seat=<%=6+10*i %>&film_num=<%=film.getMovie_num()%>&cinema_num=<%=film.getCinema_num_fk() %>&NewOrder_num=<%=request.getAttribute("NewOrder_num")%>"><%=6+10*i %></a>  			
  		</td>
  		<td class="t_3">
  		<a href="seatOrder.movies?seat=<%=7+10*i %>&film_num=<%=film.getMovie_num()%>&cinema_num=<%=film.getCinema_num_fk() %>&NewOrder_num=<%=request.getAttribute("NewOrder_num")%>"><%=7+10*i %></a>  			
  		</td>
  		<td class="t_3">
  		<a href="seatOrder.movies?seat=<%=8+10*i %>&film_num=<%=film.getMovie_num()%>&cinema_num=<%=film.getCinema_num_fk() %>&NewOrder_num=<%=request.getAttribute("NewOrder_num")%>"><%=8+10*i %></a>  			
  		</td>
  		<td class="t_3">
  		<a href="seatOrder.movies?seat=<%=9+10*i %>&film_num=<%=film.getMovie_num()%>&cinema_num=<%=film.getCinema_num_fk() %>&NewOrder_num=<%=request.getAttribute("NewOrder_num")%>"><%=9+10*i %></a>  	
  		</td>
  		<td class="t_3">
  		<a href="seatOrder.movies?seat=<%=10+10*i %>&film_num=<%=film.getMovie_num()%>&cinema_num=<%=film.getCinema_num_fk() %>&NewOrder_num=<%=request.getAttribute("NewOrder_num")%>"><%=10+10*i %></a>  			
  		</td>
  	</tr>
  	<% 	
  			}
  		}
  	%>
  	<a href="personalData.jsp">请在选完座位后前往个人中心确认订单</a>
  	</table>
  </div>
  <br>
  <hr></hr>
  <br>
  <div class="d_1">
  	<%
  		List<Comment> comments=(List<Comment>)session.getAttribute("comments");
  	%>
  影评：<p>
  	<table class="tb_1" cellspacing=0px >
  	<tr>
  		<th class="text_1">评论</th>
  		<th class="text_2">用户id</th>
  		<th class="text_2">时间戳</th>
  		<th class="text_2">回复</th>
  		<th class="text_2">删除</th>
  	</tr>
  	
  	<%
  	  		if(comments!=null){
  	  	  	for(Comment comment:comments){
  	  	%>
  	<tr>
  		<td class="text_1"><%=comment.getComment() %></td>
  		<td class="text_2"><%=comment.getPublisher() %></td>
  		<td class="text_2"><%=comment.getDate() %></td>
  		
  		<%
  			if(customer!=null){
  	
  	 	%>	
  		<td class="text_2">
  		<form action="commentsReply.movies">
  		<input type="text" name="reply"/>
  		<input type="hidden" name="beReplied" value=<%=comment.getPublisher() %>>
  		<input type="hidden" name="movie_num" value="<%=film.getMovie_num()%>"/>
  		<input type="hidden" name="publisher" value="<%=customer.getName() %>"/>
 		<input type="hidden" name="phone_fk" value="<%=customer.getPhone() %>"/>
  		<input type="submit" value="回复">
  		</form>
  		</td>
  		<%
  			}else{
  		%>
  		<td><span>会员未登录</span></td>
  		<% 		
  			}
  		 %>
  		<% 		
  			if(customer!=null){
  				if(customer.getPhone().equals(comment.getPhone_fk())){
  		%>
  		<td class="text_2"><a href="deleteComment.movies?comment_num=<%=comment.getComment_num()%>&movie_num=<%=comment.getMovie_num_fk()%>">删除评论</a></td>
  		<% 		
  				}
  			}
  		 %>
  	</tr>
  <%
 	 }
  }
  %>
  	</table>
  </div>
  <br>
  <hr></hr>
  <div class="d_1">
  <br>
  我也来评：
  <p>
  <%
  //登陆后才可评论
  	if(customer!=null){
   %>
  <form action="addComments.movies">
  <input type="text" class="comment" name="comment"/>
  <input type="hidden" name="movie_num" value="<%=film.getMovie_num()%>"/>
  <input type="hidden" name="publisher" value="<%=customer.getName() %>"/>
  <input type="hidden" name="phone_fk" value="<%=customer.getPhone() %>"/>
  <input type="submit" value="发布"/>&nbsp;&nbsp;
  <% 
  }else{
  %>
  <P>未登录，请前往<a href="login.jsp">登陆页面</a></P>
  <% 
  }
   %>
  </form>
  </div>
  <hr></hr>
  </body>
</html>
