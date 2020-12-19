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
	<h1>[회원 수정]</h1>
  <form action='<c:url value='/app/user/update'/>' method='post'>
	<input type='hidden' name='no' value='${user.no}'><br>
	<a href='../../upload/user/${user.photo}%>'> <img
		src='../../upload/user/${user.photo}_160x160.jpg'
		alt='[${user.photo}]'></a>
	<br>
	<br>
	<br>
	<p>닉네임 : <input type='text' class="form-control" name='nick' value='${user.nickname}'></p>
  <p>이메일 : <input type='text' class="form-control" name='email' value='${user.email}'></p>
	<p>이름: <input type='text' class="form-control" name='name' value='${user.name}'></p>

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
	소개:<div class="mb-3">
      <label for="exampleFormControlTextarea1" class="form-label"
        ></label>
      <textarea class="form-control" id="exampleFormControlTextarea1" rows="10" name='bio'>${user.bio}</textarea>
    </div>
	<p>기술</p>주 언어<div class="mb-3">
      <label for="exampleFormControlTextarea1" class="form-label"
        ></label>
      <textarea class="form-control" id="exampleFormControlTextarea1" rows="10" name='tech'>${user.tech}</textarea>
    </div>
	<p>개인 홈페이지: ${user.homepageURL}</p>
	<p>깃허브: ${user.githubURL}</p>
	<p>인스타그램: ${user.instarURL}</p>
	<p>트위터:${user.twitterURL}</p>
	
	<a href='list' style='color: blue;'>회원 목록으로</a>
	<button class="btn btn-primary">수정</button>
	</form>
	
	<jsp:include page="/footer.jsp"></jsp:include>
