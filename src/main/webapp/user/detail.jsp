<%@page import="com.devil.domain.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	<%
User user = (User) request.getAttribute("user");
if (user == null) {
  response.setHeader("Refresh", "2;url=list");%>
	<p>해당 번호의 회원이 없습니다.</p>
<%
} else {
%>

	<form action='updatePhoto' method='post' enctype='multipart/form-data'>
		<input type='hidden' name='no' value='<%=user.getNo()%>'><br>
		<a href='../upload/user/<%=user.getPhoto()%>'> <img
			src='../upload/user/<%=user.getPhoto()%>_160x160.jpg'
			alt='[<%=user.getPhoto()%>]'></a><br> <input type='file'
			name='photo'><br>
		<button>변경</button>
	</form>
	<br>

<form action='update' method='post'>
<input type='hidden' name='no' value='<%=user.getNo()%>'><br>
 <p>닉네임: <input type='text' name='nickname' value='<%=user.getNickname()%>'></p>
 <p>이메일: <%=user.getEmail()%></p>
 <p>이름: <%=user.getName()%></p>

<% DateFormat format = new SimpleDateFormat("yyyy/MM/dd"); %>
<p>가입일: <%=format.format(user.getCreatedDate())%></p>

<% int loginType = Integer.parseInt(user.getLoginType()); %>
<% if(loginType == 1) { 
  out.println("<p>로그인 유형: 기본 가입회원</p>");
} else if (loginType == 2) {
  out.println("<p>로그인 유형: 구글 가입회원</p>");
} else if (loginType == 3) {
  out.println("<p>로그인 유형: 깃허브 가입회원</p>");
}
%>

<p>소개: <input type='text' name='bio' value='<%=user.getBio()%>'></p>
 <p>기술: <input type='text' name='tech' value='<%=user.getTech()%>'></p>
<p>개인 홈페이지: <input type='text' name='homepage' value='<%=user.getHomepageURL()%>'></p>
<p>깃허브: <input type='text' name='githubURL' value='<%=user.getGithubURL()%>'></p>
<p>인스타그램: <input type='text' name='instarURL' value='<%=user.getInstarURL()%>'></p>
<p>트위터: <input type='text' name='twitterURL' value='<%=user.getTwitterURL()%>'></p>

<p style='color:red'><%=user.getState() == 1 ? "" : "탈퇴한 회원" %></p>
<button>정보 수정</button>
<button type='button' class='btn-danger' onclick="location.href='delete?no=<%=user.getNo()%>'">회원 탈퇴</button>
<a href='list' style='color:blue;'>회원 목록으로</a>
</form>
<%}%>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>