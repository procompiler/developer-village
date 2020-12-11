<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>${tag.name}</title>
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>
	<h1># ${tag.name}</h1>
	<button type='button' class="btn btn-primary"
		onclick="location.href='form'">글쓰기</button>

	<table border='1'>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${articleList}" var="article">
				<tr>
					<td>${article.no}</td>
					<td id='title'>
						<ul id='tags'>
							<c:forEach items="${article.tags}" var="tag">
								<li id='color' style="background-color: #${tag.tagColor};">${tag.name}</li>
							</c:forEach>
						</ul> <a href='detail?no=${article.no}'>${article.title}</a>
					</td>
					<td>${article.writer.nickname}</td>
					<td><fmt:formatDate value="${article.createdDate}"
							pattern="yyyy.MM.dd" /></td>
					<td>${article.viewCount}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<jsp:include page="/footer.jsp"></jsp:include>
	<script src="../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
