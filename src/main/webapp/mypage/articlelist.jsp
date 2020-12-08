<%@page import="java.util.ArrayList"%>
<%@page import="com.devil.domain.Tag"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.devil.domain.User"%>
<%@page import="com.devil.domain.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	
<!DOCTYPE html>
<html>
<head>
<title>마이페이지</title>
<link rel="stylesheet" type="text/css" href='../style.css'>
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<style>
#urls {
	padding: 5px;
	font-size: 2em;
}
</style>
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>
	<jsp:include page="/mypage/info"></jsp:include>
	<h2>작성글</h2>
	<table border='1'>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>등록일</th>
				<th>조회수</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${articleList}" var="a">
				<tr>
					<td>${a.no}</td>
					<td id='title'>
						<ul id='tags'>
							<c:forEach items="${a.tags}" var="tag">
								<li id='color' style="background-color: #${tag.tagColor}">${tag.name}</li>
							</c:forEach>
						</ul> 
						<a href='detail?no=${a.no}'>${a.title}</a>
					</td>
					<td><fmt:formatDate value="${a.createdDate}"
							pattern="yyyy.MM.dd" /></td>
					<td>${a.viewCount}</td>
					<td><a class='btn btn-hollow'
						href='../article/delete?articleNo=${a.no}'>삭제</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>
