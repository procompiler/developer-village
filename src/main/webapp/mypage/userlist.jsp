<%@page import="java.util.ArrayList"%>
<%@page import="com.devil.domain.Tag"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.devil.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>마이페이지</title>
<link rel="stylesheet" type="text/css" href='../style.css'>
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<style>
#urls {
	padding: 5px;
	font-size: 2em;
}
</style>
</head>
<body>
    <jsp:include page="/header.jsp"></jsp:include>
    <jsp:include page="/mypage/info"></jsp:include>
  <%
	List<User> users = (List<User>) request.getAttribute("users");
  %>
	<h2>팔로우하는 유저</h2>
	<table border='1'>
	 <thead>
		<tr>
			<th>닉네임</th>
			<th></th>
		</tr>
   <thead>
   <tbody>
		<%
		  for (User u : users) {
		%>
		<tr>
			<td><a href='../user/detail?no=<%=u.getNo()%>'> <img
					src='../upload/user/<%=u.getPhoto()%>_40x40.jpg'
					style='border-radius: 70px' alt='[<%=u.getNickname()%>]'><%=u.getNickname()%></a></td>
			<td><button type='button' class='btn-hollow'
					onclick="location.href='../follow/user/delete?uno=<%=u.getNo()%>'">언팔로우</button></td>
		  <td><a class='btn btn-hollow' href='../follow/user/delete?uno=<%=u.getNo()%>'>언팔로우</a></td>
		</tr>
		<%
		  }
		%>
		</tbody>
	</table>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>
