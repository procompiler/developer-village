<%@page import="com.devil.domain.Tag"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>게시글작성</title>

<link rel="stylesheet"
	href="../node_modules/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href='../style.css'>

</head>

<body>
	<jsp:include page="/header.jsp"></jsp:include>

	<h1>게시글 작성</h1>

	<form action='add' method='post'>
		카테고리: <select name='categoryNo'>
			<option value='1'>커뮤니티</option>
			<option value='2'>QnA</option>
			<option value='3'>채용공고</option>
			<option value='4'>스터디</option>
		</select><br> 제목: <br> <input style="font-size: 20px;" type='text'
			name='title' size='50'><br> 내용: <br>
		<textarea name='content' rows='20' cols='100'></textarea>
		<br>

		<p>
			태그: <br>
			<c:forEach items="${tags}" var="tag">
				<input type='checkbox' name='tags' value='${tag.no}'>${tag.name}
	  	<c:if test="${tag.no % 9== 0 }">
					<br>
				</c:if>
			</c:forEach>
		</p>

		<button>게시글 작성</button>
		<button type='button' class='btn-danger'
			onclick="location.href='list'">취소</button>

	</form>
	<jsp:include page="/footer.jsp"></jsp:include>
	<script src="../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>