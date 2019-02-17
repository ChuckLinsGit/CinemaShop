<%@page import="cinemaModule.entity.CityLocate"%>
<%@page import="cinemaModule.entity.movies"%>
<%@page import="customModule.entity.customer"%>
<%@page import="java.io.UnsupportedEncodingException"%> 
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'city_Cinema.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/style.css">

	<script type="text/javascript">
            
                // 创建一个二维数组
                var arr = new Array(2);
                arr[0] = ["广东","广州","深圳","佛山","湛江"];
                arr[1] = ["湖北","荆州","武汉","赤壁"];
                
                function choose(val){
                    // 获取city的select
                    var city = document.getElementById("city");
                    // 获取option
                    var cityOp = city.getElementsByTagName("option");
                    // 设置可操作
                    city.disabled = false;
                    // 先删除，后添加
                    for (var i = 0; i < cityOp.length; i++) {
                        var op = cityOp[i];
                        // 删除option
                        city.removeChild(op);
                        //数组长度发生变化，需处理
                        i--;
                    }
                    
                    // 遍历
                    for (var i = 0; i < arr.length; i++) {
                        //取一维数组
                        var arr1 = arr[i];
                        //取一维数组的第一个值
                        var firstVal = arr1[0];
                        //判断
                        if(firstVal == val){
                            //遍历
                            for (var j = 1; j < arr1.length; j++) {
                                // 获取城市名
                                var value = arr1[j];
                                // 创建option
                                var optionl = document.createElement("option");
                                // 创建文本
                                var textl = document.createTextNode(value);
                                // 把文本添加到标签
                                optionl.appendChild(textl);
                                //添加到city里面
                                city.appendChild(optionl);
                            }
                        }
                    }
                }
                
                function cityName(){
                	 return document.getElementById("city").value;
                }
            </script>

  </head>
  
  <!-- 通过*.cinema映射提交到cinemaServlet -->
  <body>
  <br>
  	<div>
  		<table>
  		<tr>
  		<td>用户：<span>
  		
  		<%
  		  			customer customer=(customer)session.getAttribute("customer");
  		  		  			if(customer==null){
  		  		%>	
  			会员未登录
  		<%
  		  			}else{
  		  		%>
  			<%=customer.getName()%>
  			</span></td>	
  		<td><a  href="personalData.jsp?phoneForDataPhone=<%=customer.getPhone()%>">个人中心</a></td>
  		<td><a href="loginoff.cust">注销用户</a></td>
  		<%
  			}
  		%>

  			
  		<!--分开两个表单按手动和自动提交 -->	
  		<td><form action="getCinemas.cinema">
  			城市:
  			<input type="hidden" value="handle" name="method"/>
  			<select id="province"  onchange="choose(this.value)">
            <option value="0">--请选择省--</option>
            <option value="广东">广东</option>
            <option value="湖北">湖北</option>
        	</select>
        
       		<select id="city" name="city" disabled="disabled">
       	    <option value="0">--请选择市--</option>
       		</select>
       		<input type="submit" value="提交手动定位结果" >
       		</form></td>
       		
       <td><form action="getCinemas.cinema">
       		<input type="submit" value="自动提交定位结果" >
       		<input type="hidden" value="auto" name="method"/>
       	<%
       		Object msg=request.getAttribute("mdg");
       	  			if(msg!=null){
       	%>
  			<tr>
  				<td>
  					<P><%=msg%></a></P>
  				</td>
  			</tr>
  		<%
  			}
  		%>
       </form></td>
       
       		
       	<td>&nbsp;&nbsp;&nbsp;<a href="index.jsp">返回首页</a></td>
  		</tr>
  		</table>
  	</div>  
  	<br>
  	<hr></hr>
  	<br>
  	<div class="d_1">
  	 
  	影院选择：<p>
  	<%
  		List<CityLocate> cinemas=(List<CityLocate>)session.getAttribute("cinemas");
  	%>
  	<table class="tb_1" cellspacing=0>
  			<tr>
  			<th class="t_1" >城市</th>
  			<th class="t_1">影院</th>
  			<th class="t_1">地址</th>
  			<th class="t_1"></th>
  			</tr>
  		<%
  			if(cinemas!=null&&cinemas.size()>0){
  		  			for(CityLocate cinema:cinemas){
  		%>
  			<tr>
  			<td class="t_2" ><%=cinema.getCity() %></td>
  			<td class="t_2"><%=cinema.getCinema() %></td>
  			<td class="t_2"><%=cinema.getAddress() %></td>
  			<!-- 传入cinema_num来跳转到相对应cinema页面 -->
  			<td class="t_2"><a href="getCinema.cinema?cinema=<%=URLEncoder.encode(cinema.getCinema(), "UTF-8")%>&cinema_num=<%=cinema.getCinema_num() %>">进入</a></td>
  			</tr>
  		
  		<%	
  			}
  		}
  		 %>
  			
  	</table>
  	</div >
  	<br>
  	<hr></hr>
  	<br>
  	<div class="d_1">
  	电影选择：<p>
  	<%
  		List<Movie> movies=(List<Movie>)session.getAttribute("movies");	
  	  			//request传回的特定的cinema
  	  			String cinema=String.valueOf(session.getAttribute("cinema"));
  	%>
  	<table class="tb_1" cellspacing=0>
  			<tr>
  			<th class="t_1" >影院</th>
  			<th class="t_1">电影</th>
  			<th class="t_1">上映时间</th>
  			<th class="t_1"></th>
  			</tr>
  			
  		<%
  			  			if(movies!=null&&movies.size()>0){
  			  		  			  		  			for(Movie film:movies){
  			  		%>
  			<tr>
  			<td class="t_2"><%=cinema %></td>
  			<td class="t_2"><%=film.getName() %></td>
  			<td class="t_2"><%=film.getDate() %></td>
  			<!-- 通过movie_num来跳转相对应的电影页面 -->
  			<td class="t_2"> <a href="getFilm.movies?movie_num=<%=film.getMovie_num()%>">进入</a></td>
  			</tr>
  		
  		<%	
  			}
  		}
  		 %>
  			
  		</table>
  	</div>
  </body>
</html>
