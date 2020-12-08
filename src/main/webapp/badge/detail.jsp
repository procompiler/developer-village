<%@page import="com.devil.domain.Badge"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뱃지 조회</title>
<link rel="stylesheet" type="text/css" href='../style.css'>
<link rel="stylesheet"
  href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
  integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
  crossorigin="anonymous" />
</head>
<body>
<h1>뱃지 조회</h1>

<%Badge badge = (Badge) request.getAttribute("badge");
if (badge == null) {
  response.setHeader("Refresh", "2;url=list");%>
  <p>해당 번호의 뱃지가 없습니다.</p>
<% 
} else {
%>

<form action='updatePhoto' method='post' enctype='multipart/form-data'>
<input type='text' name='no' value='<%=badge.getNo()%>' readonly style='display:hidden;'><br>
<img src='../upload/badge/<%=badge.getPhoto()%>' alt='[<%=badge.getPhoto()%>]'>
<input type='file' name='photo'><br>
<button>이미지 변경</button>
</form>
<form action='update' method='post'>
<input type='hidden' name='no' value='<%=badge.getNo()%>'>
<p>이름 : <%=badge.getName()%></p>
<textarea name='content'><%=badge.getContent()%></textarea><br>
<button>뱃지 수정</button>
</form>
<p><a class='btn' href='list'>뱃지 목록으로</a></p>
<a class='btn' href='delete?no=<%=badge.getNo()%>'>뱃지 삭제</a>
<%}%>
</body>
</html>