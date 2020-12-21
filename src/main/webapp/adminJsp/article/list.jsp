<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>게시글 관리</title>
<jsp:include page="/admin-header.jsp"></jsp:include>
</head>
<body>
  <div class="mini-navi">
  <a href='../article/list' style='text-decoration: none;'>자유게시판</a>
  <a href='../article/list' style='text-decoration: none;'>QnA</a>
  <a href='../article/list' style='text-decoration: none;'>채용공고</a>
  <a href='../article/list' style='text-decoration: none;'>스터디</a>
  </div>
  <h1>
    <a href='../article/list' style='text-decoration: none;'>게시글 관리</a>
  </h1>

	<c:if test="${param.keyword != null}">
  '${param.keyword}'로 검색한 결과입니다.
  </c:if>

	<form action='${contextPath}?' method='get'>
		<input type='text' class="col-sm-3" name='keyword' value=''
			placeholder="게시판 내 검색">
		<button class="btn btn-primary">검색</button>
	</form>


	<table border='1'>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>카테고리</th>
				<th>작성자</th>
				<th>등록일</th>
				<th>조회수</th>
				<th>댓글수</th>
				<th>상태</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${articles}" var="article">
				<tr>
					<td>${article.no}</td>
					<td id='title'>
						<ul id='tags'>
						</ul> <a href='detail?no=${article.no}'>${article.title}</a>
					</td>
					<td><c:choose>
							<c:when test="${article.categoryNo == 1}">
								<p>자유게시판</p>
							</c:when>
							<c:when test="${article.categoryNo == 2}">
								<p>QnA</p>
							</c:when>
							<c:when test="${article.categoryNo == 3}">
								<p>채용공고</p>
							</c:when>
							<c:when test="${article.categoryNo == 4}">
								<p>스터디</p>
							</c:when>
						</c:choose></td>
					<td>${article.writer.nickname}</td>
					<td><fmt:formatDate value="${article.createdDate}"
							pattern="yyyy.MM.dd" /></td>
					<td>${article.viewCount}</td>
					<td>${article.commentCount}</td>
					<td>${article.state == 1 ? "게시" : "미게시"}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


	<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>
