<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.devil.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
  
	<%
DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
User user = (User) request.getSession().getAttribute("loginUser");
if (user == null) {
%>
	<p>로그인 하지 않았습니다!</p>
	<%
  return;
}
%>
	<h1>로그인 사용자 정보</h1>
	<p>
		번호<%=user.getNo()%></p>
	<p>이메일: <%=user.getEmail()%></p>
	<p>닉네임: <%=user.getNickname()%></p>
	<p>이름: <%=user.getName()%></p>
	<p>가입일: <%=format.format(user.getCreatedDate()) %></p>
	<p>로그인 유형: <%=user.getLoginType().equals("1") ? "기본" : user.getLoginType().equals('2') ? "구글" : "깃허브"%> 가입회원</p>
	<p>기술 목록: <%=user.getTech() %></p>
	<img src='../upload/<%=user.getPhoto()%>.jpg' alt='[<%=user.getPhoto()%>.jpg]' height='100px'>
	<div id="urls">
	<% if (user.getHomepageURL() != null) {%>
	 <a href="<%=user.getHomepageURL()%>"><i class="fas fa-home"></i></a>
	<% } if (user.getGithubURL() != null) {%>
	 <a href="<%=user.getGithubURL()%>"><i class="fab fa-github-alt"></i></a>
	<% } if (user.getInstarURL() != null) {%>
	<a href="<%=user.getInstarURL()%>"><i class="fab fa-instagram"></i></i></a>
	<% } if (user.getTwitterURL() != null) {%>
	 <a href="<%=user.getTwitterURL()%>"><i class="fab fa-twitter"></i></i></a>
	<% } %>
	</div>
	<a href='logout'>로그아웃</a>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>
