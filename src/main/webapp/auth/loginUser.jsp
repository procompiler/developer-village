<%@page import="java.util.ArrayList"%>
<%@page import="com.devil.domain.Tag"%>
<%@page import="java.util.List"%>
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
  <jsp:include page="/header"></jsp:include>

	<%
	  DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	User user = (User) request.getSession().getAttribute("loginUser");
	List<Tag> tags = (List<Tag>) request.getAttribute("tags");
	List<User> users = (List<User>) request.getAttribute("users");

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
	<p>
		이메일:
		<%=user.getEmail()%></p>
	<p>
		닉네임:
		<%=user.getNickname()%></p>
	<p>
		이름:
		<%=user.getName()%></p>
	<p>
		가입일:
		<%=format.format(user.getCreatedDate())%></p>
	<p>
		로그인 유형:
		<%=user.getLoginType().equals("1") ? "기본" : user.getLoginType().equals('2') ? "구글" : "깃허브"%>
		가입회원
	</p>
	<p>
		기술 목록:
		<%=user.getTech()%></p>
	<img src='../upload/<%=user.getPhoto()%>.jpg'
		alt='[<%=user.getPhoto()%>.jpg]' height='100px'>
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
		<a href="<%=user.getInstarURL()%>"><i class="fab fa-instagram"></i></i></a>
		<%
		  }
		if (user.getTwitterURL() != null) {
		%>
		<a href="<%=user.getTwitterURL()%>"><i class="fab fa-twitter"></i></i></a>
		<%
		  }
		%>
	</div>

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
			<%
			  for (Tag tag : tags) {
			  if (tag.getState() == 0) {
			    continue;
			  }
			%>
			<tr>
				<td id="title"><a href='detail?no=<%=tag.getNo()%>'><%=tag.getName()%></a></td>
				<td><img src='../upload/tag/<%=tag.getPhoto()%>_80x80.png'
					alt='<%=tag.getPhoto()%>'></td>
				<td><span id="color"
					style="background-color:#<%=tag.getTagColor()%>; color:#<%=tag.getFontColor()%>"><%=tag.getName()%></span></td>
				<td><button type='button' class='btn-hollow'
						onclick="location.href='../user/unfollowTag?tno=<%=tag.getNo()%>'">
						언팔로우</button></td>
			</tr>
			<%
			  }
			%>
		</tbody>
	</table>
	<h2>팔로우하는 유저</h2>
	<table border='1'>
	 <thead>
		<tr>
			<th>닉네임</th>
			<th></th>
		</tr>
   <thead>
   <tbody>
		<%
		  for (User u : users) {
		%>
		<tr>
			<td><a href='detail?no=<%=u.getNo()%>'> <img
					src='../upload/user/<%=u.getPhoto()%>_40x40.jpg'
					style='border-radius: 70px' alt='[<%=u.getNickname()%>]'><%=u.getNickname()%></a></td>
			<td><button type='button' class='btn-hollow'
					onclick="location.href='../user/unfollowUser?uno=<%=u.getNo()%>'">언팔로우</button></td>
		</tr>
		<%
		  }
		%>
		</tbody>
	</table>
	<a href='logout'>로그아웃</a>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>
