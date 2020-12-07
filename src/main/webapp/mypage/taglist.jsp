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
    <jsp:include page="/header.jsp"></jsp:include>
    <jsp:include page="/mypage/info"></jsp:include>

  <%
	List<Tag> tags = (List<Tag>) request.getAttribute("tags");
  %>
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
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>
