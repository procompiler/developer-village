<%@page import="com.devil.domain.Tag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head><title>태그 조회</title>
<link rel="stylesheet" type="text/css" href='../style.css'></head>
<body>

<h1>태그 조회</h1>

<%Tag tag = (Tag) request.getAttribute("tag");
if (tag == null) {
  response.setHeader("Refresh", "2;url=list");%>
  <p>해당 번호의 태그가 없습니다.</p>
<% 
} else {
%>
<form action='updatePhoto' method='post' enctype='multipart/form-data'>
<input type='hidden' name='no' value='<%=tag.getNo()%>'>
<img src='../upload/tag/<%=tag.getPhoto()%>_160x160.png' alt='[<%=tag.getPhoto()%>]'>
<input type='file' name='photo'><br>
<button>이미지 변경</button>
</form>
<form action='update' method='post'>
<input type='hidden' name='no' value='<%=tag.getNo()%>'>
<p>태그색: <input type='color' name='tagColor' value='#<%=tag.getTagColor()%>'></p>
<p>폰트색: <input type='color' name='fontColor' value='#<%=tag.getFontColor()%>'></p>
<button>태그 수정</button>
</form>
<p><a href='list' style='color:white;'>태그 목록으로</a></p>
<button type='button'onclick="location.href='delete?no=<%=tag.getNo()%>'">태그 삭제</button>
<%}%>
</body>
</html>
