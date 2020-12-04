<%@page import="com.devil.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
<link rel="stylesheet" type="text/css" href='../style.css'>
<link rel="stylesheet"
  href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
  integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
  crossorigin="anonymous" />
</head>
<body>
<% User loginUser = (User) session.getAttribute("loginUser");
 %>
 <% if (loginUser == null) {
 %>
 <p>로그인 된 상태가 아닙니다!</p>
 <%} else { %>
  <p><%=loginUser.getName() %> 님 안녕히 가세요!</p>
<%}%>
</body>
</html>