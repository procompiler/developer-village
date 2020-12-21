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
	%>

	<%
	  String keyword = request.getParameter("keyword");
  %>
  <div id="search-bar">
	<span style="float: left">회원 검색</span>
	<form action='list' method='get' autocomplete="off">
	<input id="search" type="search" placeholder="닉네임 또는 이메일 입력.." style=" padding-left: 40px;">
	</form>
	</div>

  <p style="font-weight: bold">총 회원수 <span class="main-color"><%=list.size()%></span>명</p>

	<table border='1' >
	<thead>
		<tr>
			<th>번호</th>
			<th>이름(이메일)</th>
			<th>닉네임</th>
			<th>가입일</th>
			<th>최종방문일</th>
			<th>상태</th>
			<th>차단상태</th>
		</tr>
  </thead>
		<%
		  List<Integer> userNoList = new ArrayList<>();
		%>

		<%
		  for (User user : list) {
		%>
		<%
		  String loginType = null;
		%>

	 <tbody>
		<tr>
			<td><%=user.getNo()%></td>
			<td><a href='detail?no=<%=user.getNo()%>'> <img
          src='../../upload/user/<%=user.getPhoto()%>_40x40.jpg'
          style='border-radius: 70px' alt='[<%=user.getPhoto()%>_40x40]'>
          <%=user.getName()%>[<%=user.getEmail()%>]</td>
			<td><%=user.getNickname()%></a></td>
			<td><%=formatter.format(user.getCreatedDate())%></td>
			<td><%=formatter2.format(user.getRecentVisitedDate()) %></td>
			<td style='color: red;'><%=user.getState() == 1 ? "" : "탈퇴한 회원"%></td>
			<td style='color: red;'><%=user.getBlocked() == 1 ? "차단중" : "" %></td>
		</tr>
		<%}%>
		</tbody>
		</table>
		  <jsp:include page="/footer.jsp"></jsp:include>
