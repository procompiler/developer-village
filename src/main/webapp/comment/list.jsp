<%@page import="com.devil.domain.Article"%>
<%@page import="com.devil.domain.Comment"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>댓글 조회</title>
<link rel="stylesheet" type="text/css" href='../style.css'>
</head>

<body>
<hr size='3'>
<h2>Comments</h2>

<%List<Comment> comments = (List<Comment>) request.getAttribute("comments");%>

<%if (comments != null) {
  for (Comment comment : comments) {%>

<%SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");%>
<form action='../comment/update?no=<%=comment.getNo()%>&articleNo=<%=comment.getArticleNo()%>' method='post'>
<input type='hidden' name='cno' value='<%=comment.getNo()%>'>
<input type='hidden' name='arno' value='<%=comment.getArticleNo()%>'>
<img src='../upload/user/<%=comment.getWriter().getPhoto()%>.jpg_40x40.jpg' alt='[<%=comment.getWriter().getPhoto()%>].jpg]'>
<a href='../user/detail?no=<%=comment.getWriter().getNo()%>'><%=comment.getWriter().getNickname()%></a>
<textarea name='content' style="height:30px;width:400px;"><%=comment.getContent()%></textarea>
<%=formatter.format(comment.getCreatedDate())%>
<button>수정</button>

<button type='button' class='btn-danger' onclick="location.href='../comment/delete?no=<%=comment.getNo()%>&articleNo=<%=comment.getArticleNo()%>'">삭제</button>
<%=comment.getState() == 1 ? "" : "삭제된 댓글"%>
</form>

<hr color='gray'>
<%}%>
<form method='post' action='../comment/add'>
<input type='hidden' name="arno" value='14' readonly><br>
<input type='text' name='content'><br>
<button>댓글쓰기</button>
</form>
<%}%>
</body>
</html>
