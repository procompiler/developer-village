<%@page import="com.devil.domain.Article"%>
<%@page import="com.devil.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고하기</title>
<link rel="stylesheet"
  href="../node_modules/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href='../style.css'>
<link rel="stylesheet"
  href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
  integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
  crossorigin="anonymous" />
</head>
<body>
<h1>게시글 신고하기</h1>

<%
 Article article = (Article) request.getAttribute("reportedArticle");
%>

<form action="reportArticle-send" method="get">
<h2>신고사유</h2>
<input type='hidden' name='articleNo' value='<%=article.getNo()%>'>

<select name="reason">
  <option value="1">욕설</option>
  <option value="2">권리침해</option>
  <option value="3">폭력적 또는 혐오성 게시글</option>
  <option value="4">불법광고</option>
  <option value="5">음란성</option>
  <option value="6">도배</option>
</select><br>
<button>신고하기</button>
</form>
<script src="../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>