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
	<jsp:include page="/appJsp/user/info.jsp"></jsp:include>
	<h2>팔로워</h2>
	<div class="row row-cols-1 row-cols-md-4 row-cols-lg-5">
	<c:forEach items="${userList}" var="u">
		<div class="col">
			<div class="card h-100">
				<div class="mx-auto p-3">
						<a href='../user/detail?no=${u.no}'> <img class="card-img-top"
							src='../../upload/user/${u.photo}_80x80.jpg'
							style='border-radius: 70px' alt='[${u.nickname}]' />
						</a>
					</div>
				<div class="card-body mx-auto">
					<h5 class="card-title text-center"><a href='../user/detail?no=${u.no}'>${u.nickname}</a></h5>
					<c:choose>
					<c:when test="${u.followed}">
					<a class='btn btn-outline-danger'href='deleteUser?followeeNo=${u.no}'>언팔로우</a>
					</c:when>
					<c:otherwise>
					<a class='btn btn-primary'href='addUser?followeeNo=${u.no}'>팔로우</a>
					</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</c:forEach>
	</div>
	<jsp:include page="/footer.jsp"></jsp:include>