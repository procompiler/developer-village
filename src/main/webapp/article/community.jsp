<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.devil.domain.Tag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<title>커뮤니티</title>
<jsp:include page="/header.jsp"></jsp:include>
<h1>커뮤니티</h1>
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
		  List<Tag> list = (List<Tag>) request.getAttribute("list");
		List<Tag> userTagNoList = (List<Tag>) request.getAttribute("userTagNoList");
		for (Tag tag : list) {
		  boolean followed = userTagNoList.contains(tag.getNo());
		  if (tag.getState() == 0) {
		    continue;
		  }
		%>
		<tr>
			<td id="title"><a href='list?tagNo=<%=tag.getNo()%>'><%=tag.getName()%></a></td>
			<td><img src='../upload/tag/<%=tag.getPhoto()%>_80x80.png'
				alt='<%=tag.getPhoto()%>'></td>
			<td><span id="color"
				style="background-color:#<%=tag.getTagColor()%>; color:#<%=tag.getFontColor()%>"><%=tag.getName()%></span></td>
			<td><a class="btn <%=followed ? " btn-hollow" : ""%>"
				href="../follow/tag/<%=followed ? "delete" : "add"%>?tno=<%=tag.getNo()%>">
					<%=followed ? "언팔로우" : "팔로우"%></a></td>
		</tr>
		<%
		  }
		%>
	</tbody>
</table>
<jsp:include page="/footer.jsp"></jsp:include>
