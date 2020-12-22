<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글작성</title>
	<jsp:include page="/header.jsp"></jsp:include>
</head>
<body>

	<h1>게시글 작성</h1>
	<br>
	<form action='add' method='post'>
		카테고리 <select class="form-select" aria-label="Default select example"
			name='categoryNo'>
    	<option value='1'>자유게시판</option> 
			<option value='2'>QnA</option>
			<option value='3'>채용공고</option>
			<option value='4'>스터디</option>
		</select>
<br>
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label"></label>
			<input type="text" class="form-control"
				id="exampleFormControlInput1" placeholder="게시글 제목"
				name='title'>
		</div>
		<div class="mb-3">
			<label for="exampleFormControlTextarea1" class="form-label"
				></label>
			<textarea class="form-control" id="exampleFormControlTextarea1" placeholder="게시글을 작성하세요!"
				rows="20" name='content'></textarea>
		</div>

		<p>
			태그<br>
			<c:forEach items="${tags}" var="tag">
					<input class="form-check-input" type="checkbox" value="${tag.no}"
						id="flexCheckDefault" name="tagNo"> <label
						class="form-check-label" for="flexCheckDefault">
						${tag.name}</label>
				<c:if test="${tag.no % 9== 0 }"><br></c:if>
			</c:forEach>
		</p>

		<button type="submit" class="btn btn-primary">게시글 작성</button>
		<button type='button' class='btn btn-danger'
			onclick="location.href='list'">취소</button>

	</form>
	
	<jsp:include page="/footer.jsp"></jsp:include>
	<script src="../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>