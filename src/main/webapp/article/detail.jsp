<%@page import="com.devil.domain.Article"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head><title>게시글 조회</title>
<link rel="stylesheet" type="text/css" href='../style.css'>
</head>

<body>
<h1>[게시물 조회]</h1>

<%
Article article = (Article) request.getAttribute("article");
if (article == null) {
  response.setHeader("Refresh", "2;url=list");%>
  <p>해당 게시글이 없습니다</p>
<%} else{%>

<form action='update' method='post'>
<input type='hidden' name='no' value='<%=article.getNo()%>'><br>
<input type='text' name='title' value='<%=article.getTitle()%>'><br>
<p>작성자: <%=article.getWriter().getNickname()%></p>

<%String categoryName = null;
switch (article.getCategoryNo()) {
  case 1: categoryName = "커뮤니티"; break;
  case 2: categoryName = "QnA"; break;
  case 3: categoryName = "채용공고"; break;
  default :categoryName = "스터디"; break;
}%>

<p>카테고리: <%=categoryName%></p>

<%SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");%>
<p>등록일: <%=formatter.format(article.getCreatedDate())%></p>
<p>조회수: <%=article.getViewCount()%></p>
<textarea name='content'><%=article.getContent()%></textarea><br>
<button>수정</button>
<button type='button' class='btn-danger' onclick="location.href='delete?no=<%=article.getNo()%>'">삭제</button>
<button type='button' class='btn-danger' onclick="location.href='../report/report-article.html'">게시글 신고</a></button>
</form>
<%}%>
</body>
</html>