<%@page import="java.util.ArrayList"%>
<%@page import="com.devil.domain.Tag"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.devil.domain.User"%>
<%@page import="com.devil.domain.Article"%>
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
	List<Article> bookmarkList = (List<Article>) request.getAttribute("bookmarkList");
  %>
	<h2>북마크리스트</h2>
	<table border='1'>
	 <thead>
		<tr>
			<th>제목</th>
			<th></th>
		</tr>
   <thead>
   <tbody>
		<%
		  for (Article a : bookmarkList) {
		%>
		<tr>
			<td><a href='../article/detail?no=<%=a.getNo()%>'><%=a.getTitle()%></a></td>
			<td><button type='button' class='btn-hollow'
					onclick="location.href='../bookmark/delete?articleNo=<%=a.getNo()%>'">북마크취소</button></td>
		</tr>
		<%
		  }
		%>
		</tbody>
	</table>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>
