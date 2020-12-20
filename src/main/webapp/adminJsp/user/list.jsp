<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.devil.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
	  <jsp:include page="/admin-header.jsp"></jsp:include>
	  </head>
	  <body>
	<div class="mini-navi">
	<a href='../user/list' style='text-decoration: none;'>전체 회원관리</a>
  <a href='../report/list' style='text-decoration: none;'>신고내역</a>
  <a href='../block/list' style='text-decoration: none;'>활동정지회원</a>
  </div>
	<h1>
		<a href='../user/list' style='text-decoration: none;'>전체 회원관리</a>
	</h1>

	<%
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	%>
	<%
	  List<User> list = (List<User>) request.getAttribute("list");
	  List<User> followingUsers = (List<User>) request.getAttribute("followingUsers");
	%>
	<%
	  User loginUser = (User) request.getSession().getAttribute("loginUser");
	%>

	<%
	  String keyword = request.getParameter("keyword");
  %>
  <div id="search-bar">
	<span style="float: left">회원 검색</span>
	<form action='userList' method='get'>
	<input id="search" type="search" placeholder="닉네임 또는 이메일 입력.." style=" padding-left: 40px;">
	</form>
	</div>

  <p style="font-weight: bold">총 회원수 <span class="main-color"><%=list.size()%></span>명</p>
	<table border='1'>
		<tr>
			<th>번호</th>
			<th>이름(이메일)</th>
			<th>닉네임</th>
			<th>가입일</th>
			<th>최종방문일</th>
			<th>상태</th>
			<th>차단상태</th>
		</tr>

		<%
		  List<Integer> userNoList = new ArrayList<>();
		%>
		<%
		  for (User u : followingUsers) {
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

		<tr>
			<td><%=user.getNo()%></td>
			<td><a href='userDetail?no=<%=user.getNo()%>'> <img
          src='../../upload/user/<%=user.getPhoto()%>_40x40.jpg'
          style='border-radius: 70px' alt='[<%=user.getPhoto()%>_80x80]'>
          <%=user.getName()%>[<%=user.getEmail()%>]</td>
			<td><%=user.getNickname()%></a></td>
			<td><%=formatter.format(user.getCreatedDate())%></td>
			<td><%=formatter2.format(user.getRecentVisitedDate()) %></td>
			<td style='color: red;'><%=user.getState() == 1 ? "" : "탈퇴한 회원"%></td>
			<td style='color: red;'><%=user.getBlocked() == 1 ? "차단중" : "" %></td>
		</tr>
		<%}%>
		</table>
		  <jsp:include page="/footer.jsp"></jsp:include>
		  <script src="../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>