<%@page import="com.devil.domain.Block"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>차단 알림</title>
<link rel="stylesheet"
	href="../node_modules/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href='../style.css'>
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>
	<h1>차단 알림</h1>

<%
  Block block = (Block)request.getAttribute("blockedUser");
%>

<%
Calendar blockTermination = Calendar.getInstance();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
%>

	<h3>현재 차단중입니다.</h3>

  <p>차단 사유 : </p>
  <p>block.getBlockedReason()</p>

  <p>차단 기한 : </p>
  <p>
   <%
   blockTermination.setTime(block.getPermittedDate());
   blockTermination.add(Calendar.DATE, block.getBlockedDates());
   %>
  </p>
	<script src="../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>