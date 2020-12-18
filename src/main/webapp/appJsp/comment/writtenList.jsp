<%@page import="java.util.ArrayList"%>
<%@page import="com.devil.domain.Tag"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.devil.domain.User"%>
<%@page import="com.devil.domain.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>마이페이지</title>
<jsp:include page="/header.jsp"></jsp:include>
<jsp:include page="/app/user/info.jsp"></jsp:include>

<h2>작성댓글</h2>
<table border='1'>
	<thead>
		<tr>
			<th>게시글제목</th>
			<th>댓글내용</th>
			<th>등록일</th>
			<th>삭제</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${commentList}" var="c">
			<tr>
				<td>${c.articleTitle}</td>
				<td id='content'><a href='detail?no=${c.articleNo}'>${c.content}</a>
				</td>
				<td><fmt:formatDate value="${c.createdDate}"
						pattern="yyyy.MM.dd" /></td>
				<td><a class='btn btn-outline-danger'
					href='delete?commentNo=${c.no}'>삭제</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>
