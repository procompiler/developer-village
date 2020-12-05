<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.devil.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
<link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>
	<a href='list' style='text-decoration: none; color=white' >전체회원관리</a>
	<a href='../report/list' style='text-decoration: none; color=white' >신고내역</a>
	<a href='list' style='text-decoration: none; color=white' >활동정지회원</a>
	<h1>
		<a href='list' style='text-decoration: none;'>전체회원관리</a>
	</h1>
	<br>

	<%
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	%>
	<%
	  List<User> list = (List<User>) request.getAttribute("list");
	%>
	<%
	  User loginUser = (User) request.getSession().getAttribute("loginUser");
	%>



	<p>
	<%
	  String keyword = request.getParameter("keyword");
  %>
	<form action='list' method='get'>
		<input type='text' placeholder="닉네임 또는 이메일 입력.." name='keyword'	value='<%=keyword != null ? keyword : ""%>'>
		<button>유저 검색</button>
	</form>
	</p>

	<table border='1'>
		<tr>
			<th>번호</th>
			<th>이메일</th>
			<th>닉네임</th>
			<th>이름</th>
			<th>가입일</th>
			<th>상태</th>
			<th>로그인타입</th>
			<th>차단상태</th>
			<th></th>
		</tr>

		<%
		  List<Integer> userNoList = new ArrayList<>();
		%>
		<%
		  for (User u : loginUser.getUsers()) {
		  userNoList.add(u.getNo());
		}
		%>

		<%
		  for (User user : list) {
		%>
		<%
		  boolean followed = userNoList.contains(user.getNo());
		%>
		<%
		  String loginType = null;
		%>
		<%
		  switch (user.getLoginType()) {
		  case "1":
		    loginType = "기본";
		    break;
		  case "2":
		    loginType = "구글";
		    break;
		  case "3":
		    loginType = "깃허브";
		    break;
		}
		%>

		<tr>
			<td><%=user.getNo()%></td>
			<td><%=user.getEmail()%></td>
			<td><a href='detail?no=<%=user.getNo()%>'> <img
					src='../upload/user/<%=user.getPhoto()%>_40x40.jpg'
					style='border-radius: 70px' alt='[<%=user.getPhoto()%>_80x80]'><%=user.getNickname()%></a></td>
			<td><%=user.getName()%></td>
			<td><%=formatter.format(user.getCreatedDate())%></td>
			<td style='color: red;'><%=user.getState() == 1 ? "" : "탈퇴한 회원"%></td>
			<td><%=loginType%></td>
			<td><%=user.getBlocked() == 1 ? "차단중" : ""%></td>
			<td><button type='button'
					<%=followed ? "class='btn-hollow'" : ""%>
					onclick="location.href='../user/<%=followed ? "un" : ""%>followUser?uno=<%=user.getNo()%>'">
					<%=followed ? "언팔로우" : "팔로우"%>
			</button></td>
		</tr>
		<%}%>
		</table>
		  <jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>