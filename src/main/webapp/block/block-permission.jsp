<%@page import="com.devil.domain.Report"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>차단하기</title>
<link rel="stylesheet" type="text/css" href='../style.css'>
</head>
<body>
  <jsp:include page="/header"></jsp:include>

	<h1>유저 차단하기</h1>
	<%
Report report = (Report) request.getAttribute("report");
%>

	
	<form action="block-permission" method="post">
		차단일수 : <select name="blockingDate">
			<option value="1">1일</option>
			<option value="2">3일</option>
			<option value="3">7일</option>
			<option value="4">30일</option>
			<option value="5">영구차단</option>
		</select><br> 차단사유 :
		<textarea name="block-reason" cols="60" rows="10"></textarea>
		<br>
		<button>차단하기</button>
		<input type='hidden' name='reportNo' value='<%=report.getNo()%>'><br></td>
    <input type='hidden' name='reportedUser' value='<%=report.getReportedArticle().getWriter().getNo()%>'><br>
	</form>
	 <jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>