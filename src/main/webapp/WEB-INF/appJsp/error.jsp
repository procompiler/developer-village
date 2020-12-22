<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.StringWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서버오류</title>
</head>
<body>
<h1>서버 오류</h1>
<%
Exception e = (Exception) request.getAttribute("exception");
%>
<pre><%=e.getMessage()%></pre>

<h3>상세 오류 내용</h3>
<% 
StringWriter errOut = new StringWriter();
e.printStackTrace(new PrintWriter(errOut));
%>

<pre><%=errOut.toString()%></pre>
</body>
</html>