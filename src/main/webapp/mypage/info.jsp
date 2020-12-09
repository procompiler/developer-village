<%@page import="java.util.ArrayList"%>
<%@page import="com.devil.domain.Tag"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.devil.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h1>로그인 사용자 정보</h1>
<p>이메일 ${loginUser.email}</p>
<p>닉네임: ${loginUser.nickname}</p>
<p>이름: ${loginUser.name}</p>
<p>
	가입일
	<fmt:formatDate value="${loginUser.createdDate}" pattern="yyyy.MM.dd" />
<p>기술 목록 ${loginUser.tech}</p>
<img src='../upload/${loginUser.photo}_160x160.jpg'
	alt='[${loginUser.photo}.jpg]'>
<div>
	<c:choose>
		<c:when test="${empty loginUser.homepageURL}">
			<a href="${loginUser.homepageURL}"><i class="fas fa-home"></i></a>
		</c:when>
		<c:when test="${empty loginUser.githubURL}">
			<a href="${loginUser.githubURL}"><i class="fab fa-github-alt"></i></a>
		</c:when>
		<c:when test="${empty loginUser.instarURL}">
			<a href="${loginUser.instarURL}"><i class="fab fa-instagram"></i></a>
		</c:when>
		<c:when test="${empty loginUser.twitterURL}">
			<a href="${loginUser.twitterURL}"><i class="fab fa-twitter"></i></a>
		</c:when>
	</c:choose>
</div>
<a class="btn" href="articlelist">작성게시글</a>
<a class="btn" href="taglist">팔로잉 태그</a>
<a class="btn" href="userlist">팔로잉 유저</a>
<a class="btn" href="bookmarklist">북마크</a>
</body>
</html>
