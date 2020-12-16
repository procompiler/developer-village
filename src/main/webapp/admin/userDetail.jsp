<%@page import="com.devil.domain.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 조회</title>
<jsp:include page="/header.jsp"></jsp:include>
</head>
<body>
	<h1>[회원 상세조회]</h1>

	<input type='hidden' name='no' value='${user.no}'>
	<br>
	<a href='../../upload/user/${user.photo}%>'> <img
		src='../../upload/user/${user.photo}_160x160.jpg'
		alt='[${user.photo}]'></a>
	<br>
	<br>
	<input type='hidden' name='no' value='${user.no}'>
	<br>
	<p>닉네임: ${user.nickname}</p>
	<p>이메일: ${user.email}</p>
	<p>이름: ${user.name}</p>

	<p>
		가입일:
		<fmt:formatDate value="${user.createdDate}" pattern="yyyy.MM.dd" />
	</p>

	<c:choose>
		<c:when test="${user.loginType == 1}">
			<p>기본 가입회원</p>
		</c:when>
		<c:when test="${user.loginType == 2}">
			<p>구글 가입회원</p>
		</c:when>
		<c:when test="${user.loginType == 3}">
			<p>깃허브 가입회원</p>
		</c:when>
	</c:choose>
	<p>소개: ${user.bio}</p>
	<p>기술: ${user.tech}</p>
	<p>개인 홈페이지: ${user.homepageURL}</p>
	<p>깃허브: ${user.githubURL}</p>
	<p>인스타그램: ${user.instarURL}</p>
	<p>트위터:${user.twitterURL}</p>
	<%-- 		<% boolean followed = (Boolean) request.getAttribute("followed"); %>
		<a class="btn <%=followed ? "btn-outline-danger" : "btn-primary"%>" 
			href='../follow/user/<%=followed ? "delete" : "add"%>?followeeNo=${user.no}'>
			<%=followed ? "언팔로우" : "팔로우"%>
		</a> --%>
	<c:if test="${user.state == 1}">
      탈퇴한 회원
      </c:if>
	<a href='list' style='color: blue;'>회원 목록으로</a>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>
