<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

	<h2>팔로우하는 태그</h2>
	<table border='1'>
		<thead>
			<tr>
				<th>태그이름</th>
				<th>태그사진</th>
				<th>미리보기</th>
				<th></th>
			</tr>
		</thead>

		<tbody>
				<c:forEach items="${tags}" var="t">
			<tr>
					<c:if test="${t.state == 0}">
        <<continue>> 
					</c:if>
					<td id="title"><a href='../tag/detail?no=${t.no}'>${t.name}</a></td>
					<td><img src='../upload/tag/${t.photo}%>_80x80.png'
						alt='${t.photo}'></td>
					<td><span id="color" style="background-color: #${t.tagColor};">${t.name}</span></td>
					<td><a class='btn btn-outline-danger'
						href='../follow/tag/delete?tno=${t.no}'>언팔로우</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>
