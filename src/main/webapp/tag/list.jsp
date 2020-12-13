
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.devil.domain.Tag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>태그목록</title>
<jsp:include page="/header.jsp"></jsp:include>

<h1>태그 목록</h1>

<button type='button' class='btn btn-primary'
	onclick="location.href='form'">태그 추가</button>


<form action='${contextPath}?' method='get'>
	<input type='text' class="col-sm-3" name='keyword' value=''
		placeholder="태그 검색">
	<button class="btn btn-primary">검색</button>
	<c:if test="${param.keyword != null}">
  '${param.keyword}'(으)로 검색한 결과입니다.
  </c:if>
</form>

<table border='1'>
	<thead>
		<tr>
			<th>번호</th>
			<th>태그이름</th>
			<th>태그사진</th>
			<th>미리보기</th>
			<th>삭제여부</th>
			<th>팔로우</th>
		</tr>
	</thead>
	<tbody>
		<%
		List<Tag> tags = (List<Tag>) request.getAttribute("tags");
		List<Tag> userTagNoList = (List<Tag>) request.getAttribute("userTagNoList");
		for (Tag tag : tags) {
		  boolean followed = userTagNoList.contains(tag.getNo());
		%>
		<tr>
			<td><%=tag.getNo()%></td>
			<td id="title"><a href='detail?no=<%=tag.getNo()%>'><%=tag.getName()%></a></td>
			<td><img src='../../upload/tag/<%=tag.getPhoto()%>_80x80.png'
				alt='<%=tag.getPhoto()%>'></td>
			<td><span id="color"
				style="background-color:<%=tag.getTagColor()%>; color:<%=tag.getFontColor()%>"><%=tag.getName()%></span></td>
			<td><%=tag.getState() == 1 ? "" : "삭제됨"%></td>
			<td><a class="btn <%=followed ? " btn-outline-danger" : "btn-primary"%>"
				href="../follow/tag/<%=followed ? "delete" : "add"%>?followeeNo=<%=tag.getNo()%>">
					<%=followed ? "언팔로우" : "팔로우"%></a></td>
		</tr>
		<%
		  }
		%>
	</tbody>
</table>
<jsp:include page="/footer.jsp"></jsp:include>
