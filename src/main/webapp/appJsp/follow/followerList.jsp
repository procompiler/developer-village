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

</head>
<body>
    <jsp:include page="/header.jsp"></jsp:include>
    <jsp:include page="/app/user/info.jsp"></jsp:include>
	<h2>팔로워</h2>
	<table border='1'>
	 <thead>
		<tr>
			<th>닉네임</th>
			<th></th>
		</tr>
   <thead>
   <tbody>
   <c:forEach items="${userList}" var="u">
		<tr>
			<td><a href='../user/detail?no=${u.no}'> <img
					src='../upload/user/${u.photo}_40x40.jpg'
					style='border-radius: 70px' alt='[${u.nickname}]'>${u.nickname}</a></td>
		  <td><a class='btn btn-outline-danger' href='delete?followeeNo=${u.no}'>언팔로우</a></td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>
