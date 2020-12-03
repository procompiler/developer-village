<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.devil.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 목록</title>
<link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>
	<h1>
		<a href='list' style='text-decoration: none;'>유저 목록</a>
	</h1>
	<a href='add' style='color: green;'>회원 가입</a>
	<br>

	<%
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	%>
	<%
	  List<User> list = (List<User>) request.getAttribute("list");
	%>


	<p>
	<form action='list' method='get'>
		<input type='text' placeholder="닉네임 또는 이메일 입력.." name='keyword'	value='%s'>
		 keyword != null ? keyword : "" out.println("
		<button>유저 검색</button>
	</form>
	</p>
	
	<table border='1'>
      <tr>
          <th>번호</th>
          <th>이메일</th>
          <th>닉네임</th>
          <th>이름</th>
          <th>가입일</th>
          <th>상태</th>
          <th>로그인타입</th>
          <th>차단상태</th>
          <th></th>
          </tr>

</body>
</html>