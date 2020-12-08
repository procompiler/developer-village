<%@page import="com.devil.domain.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 조회</title>
<link rel="stylesheet" type="text/css" href='../style.css'>
</head>
<body>
  <jsp:include page="/header.jsp"></jsp:include>
	<h1>[회원 상세조회]</h1>

	<form action='updatePhoto' method='post' enctype='multipart/form-data'>
		<input type='hidden' name='no' value='${user.no}'><br>
		<a href='../upload/user/${user.photo}%>'> <img
			src='../upload/user/${user.photo}_160x160.jpg'
			alt='[${user.photo}]'></a><br> <input type='file'
			name='photo'><br>
		<button>변경</button>
	</form>
	<br>

<form action='update' method='post'>
<input type='hidden' name='no' value='${user.no}'><br>
 <p>닉네임: <input type='text' name='nickname' value='${user.nickname}''></p>
 <p>이메일: ${user.email}'</p>
 <p>이름: ${user.name}'</p>

<fmt:parseDate value=${user.} pattern="yyyy-MM-dd" var="d1"/>

<% DateFormat format = new SimpleDateFormat("yyyy/MM/dd"); %>
<p>가입일: <%=format.format(user.createdDate)%></p>

<%-- <% int loginType = Integer.parseInt(user.getLoginType()); %>
<% if(loginType == 1) { 
  out.println("<p>로그인 유형: 기본 가입회원</p>");
} else if (loginType == 2) {
  out.println("<p>로그인 유형: 구글 가입회원</p>");
} else if (loginType == 3) {
  out.println("<p>로그인 유형: 깃허브 가입회원</p>");
}
%>
 --%>
<p>소개: <input type='text' name='bio' value='${user.bio}'></p>
 <p>기술: <input type='text' name='tech' value='${user.tech}'></p>
<p>개인 홈페이지: <input type='text' name='homepage' value='${user.homepageURL}'></p>
<p>깃허브: <input type='text' name='githubURL' value='${user.githubURL}'></p>
<p>인스타그램: <input type='text' name='instarURL' value='${user.instarURL}'></p>
<p>트위터: <input type='text' name='twitterURL' value='${user.twitterURL}'></p>

<%-- <p style='color:red'><%=user.getState() == 1 ? "" : "탈퇴한 회원" %></p>
 --%><button>정보 수정</button>
<button type='button' class='btn-danger' onclick="location.href='delete?no=${user.no}'">회원 탈퇴</button>
<a href='list' style='color:blue;'>회원 목록으로</a>
</form>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>