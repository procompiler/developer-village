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
	<h1>[회원 상세조회]</h1>

	<form action='updatePhoto' method='post' enctype='multipart/form-data'>
		<input type='hidden' name='no' value='${user.no}'><br> <a
			href='../../upload/user/${user.photo}%>'> <img
			src='../../upload/user/${user.photo}_160x160.jpg' alt='[${user.photo}]'></a><br>
		<input type='file' name='photo'><br>
		<button>변경</button>
	</form>
	<br>

	<form action='update' method='post'>
		<input type='hidden' name='no' value='${user.no}'><br>
		<p>
			닉네임: <input type='text' name='nickname' value='${user.nickname}'>
		</p>
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

		<p>
			소개: <input type='text' name='bio' value='${user.bio}'>
		</p>
		<p>
			기술: <input type='text' name='tech' value='${user.tech}'>
		</p>
		<p>
			개인 홈페이지: <input type='text' name='homepage'
				value='${user.homepageURL}'>
		</p>
		<p>
			깃허브: <input type='text' name='githubURL' value='${user.githubURL}'>
		</p>
		<p>
			인스타그램: <input type='text' name='instarURL' value='${user.instarURL}'>
		</p>
		<p>
			트위터: <input type='text' name='twitterURL' value='${user.twitterURL}'>
		</p>
<%-- 		<% boolean followed = (Boolean) request.getAttribute("followed"); %>
		<a class="btn <%=followed ? "btn-outline-danger" : "btn-primary"%>" 
			href='../follow/user/<%=followed ? "delete" : "add"%>?followeeNo=${user.no}'>
			<%=followed ? "언팔로우" : "팔로우"%>
		</a> --%>
		<c:if test="${user.state == 1}">
      탈퇴한 회원
      </c:if>
		<%-- <p style='color:red'><%=user.getState() == 1 ? "" : "탈퇴한 회원" %></p>
 --%>
		<button>정보 수정</button>
		<button type='button' class='btn-danger'
			onclick="location.href='delete?no=${user.no}'">회원 탈퇴</button>
		<a href='list' style='color: blue;'>회원 목록으로</a>
	</form>
	<jsp:include page="/footer.jsp"></jsp:include>
