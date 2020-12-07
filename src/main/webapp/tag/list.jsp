<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.devil.domain.Tag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>태그목록</title>
<link rel="stylesheet" type="text/css" href='../style.css'>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
  integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />

</head>
<body>
    <jsp:include page="/header.jsp"></jsp:include>
	<h1>태그 목록</h1>
	<button type='button' onclick="location.href='form.html'">태그
		추가</button>

	<table border='1'>
		<thead>
			<tr>
				<th>번호</th>
				<th>태그이름</th>
				<th>태그사진</th>
				<th>미리보기</th>
				<th>삭제여부</th>
				<th></th>
			</tr>
		</thead>

		<tbody>
			<%
			  List<Tag> list = (List<Tag>) request.getAttribute("list");
			  List<Tag> userTagNoList = (List<Tag>) request.getAttribute("userTagNoList");
 			for (Tag tag : list) {
			  boolean followed = userTagNoList.contains(tag.getNo());
			%>
			<tr>
				<td><%=tag.getNo()%></td>
				<td id="title"><a href='detail?no=<%=tag.getNo()%>'><%=tag.getName()%></a></td>
				<td><img src='../upload/tag/<%=tag.getPhoto()%>_80x80.png'
					alt='<%=tag.getPhoto()%>'></td>
				<td><span id="color"
					style="background-color:#<%=tag.getTagColor()%>; color:#<%=tag.getFontColor()%>"><%=tag.getName()%></span></td>
				<td><%=tag.getState() == 1 ? "" : "삭제됨"%></td>
				<td><button type='button'
						<%=followed ? "class='btn-hollow'" : ""%>
						onclick="location.href='../user/<%=followed ? "un" : ""%>followTag?tno=<%=tag.getNo()%>'">
						<%=followed ? "언팔로우" : "팔로우"%></button></td>
			</tr>
			<%
			  }
			%>
		</tbody>
	</table>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>
