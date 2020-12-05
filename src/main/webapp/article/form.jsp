
<%@page import="com.devil.domain.Tag"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>게시글작성</title>
<link rel="stylesheet" type="text/css" href='../style.css'>
</head>

<body>
  <jsp:include page="/header.jsp"></jsp:include>
<h1>게시글 작성</h1>
<form action='add' method='post'>
글 제목: <input type='text' name='title'>
카테고리: 
<select name='categoryNo'>
	<option value='1'>커뮤니티</option>
	<option value='2'>QnA</option>
	<option value='3'>채용공고</option>
	<option value='4'>스터디</option>
</select><br>

내용: <br>
<textarea name='content' rows='20' cols='100'></textarea><br>

태그: 
<ul>
<%List<Tag> tags = (List<Tag>) request.getAttribute("tags");
for (Tag t : tags) {%>
<input type='checkbox' name='tags' value='<%=t.getNo()%>'><%=t.getName()%>
<%if(t.getNo() != 0) { if(t.getNo() % 7 == 0) {%> <br> <%} }%>
<%}%>
</ul>

<button>게시글 작성</button>
<button type='button' class='btn-danger' onclick="location.href='list'">취소</button>

</form>

  <jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>