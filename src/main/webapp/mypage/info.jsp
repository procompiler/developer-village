<%@page import="java.util.ArrayList"%>
<%@page import="com.devil.domain.Tag"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.devil.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
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
		이메일
		<%=user.getEmail()%></p>
	<p>
		닉네임
		<%=user.getNickname()%></p>
	<p>
		이름
		<%=user.getName()%></p>
	<p>
		가입일
		<%=format.format(user.getCreatedDate())%></p>
	<p>
		로그인 유형
		<%=user.getLoginType().equals("1") ? "기본" : user.getLoginType().equals('2') ? "구글" : "깃허브"%>
		가입회원
	</p>
	<p>
		기술 목록
		<%=user.getTech()%></p>
	<img src='../upload/<%=user.getPhoto()%>_160x160.jpg'
		alt='[<%=user.getPhoto()%>.jpg]'>
	<div id="urls">
		<%
		if (user.getHomepageURL() != null) {
		%>
		<a href="<%=user.getHomepageURL()%>"><i class="fas fa-home"></i></a>
		<%
		  }
		if (user.getGithubURL() != null) {
		%>
		<a href="<%=user.getGithubURL()%>"><i class="fab fa-github-alt"></i></a>
		<%
		  }
		if (user.getInstarURL() != null) {
		%>
		<a href="<%=user.getInstarURL()%>"><i class="fab fa-instagram"></i></a>
		<%
		  }
		if (user.getTwitterURL() != null) {
		%>
		<a href="<%=user.getTwitterURL()%>"><i class="fab fa-twitter"></i></a>
		<%
		  }
		%>
	</div>
		<a class="btn btn-black" href="articlelist">작성게시글</a>
		<a class="btn btn-black" href="taglist">팔로잉 태그</a>
		<a class="btn btn-black" href="userlist">팔로잉 유저</a>
		<a class="btn btn-black" href="bookmarklist">북마크</a>
</body>
</html>
