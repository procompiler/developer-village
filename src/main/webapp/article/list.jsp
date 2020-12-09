<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>게시글목록</title>
<link rel="stylesheet" type="text/css" href='../style.css'>
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
	
	<link rel="stylesheet"
  href="../node_modules/bootstrap/dist/css/bootstrap.min.css">
</head>

<body>
	<jsp:include page="/header.jsp"></jsp:include>

	<h1>
		<c:choose>
			<c:when test="${param.categoryNo == 1}">
				<p>커뮤니티</p>
			</c:when>
			<c:when test="${param.categoryNo == 2}">
				<p>QnA</p>
			</c:when>
			<c:when test="${param.categoryNo == 3}">
				<p>채용공고</p>
			</c:when>
			<c:when test="${param.categoryNo == 4}">
				<p>스터디</p>
			</c:when>
			<c:otherwise>
				<p>전체 게시글</p>
			</c:otherwise>
		</c:choose>
	</h1>
	<button type='button' onclick="location.href='form'">글쓰기</button>
  
  <c:if test="${param.keyword != null}">
  '${param.keyword}'로 검색한 결과입니다.
  </c:if>
  
	<table border='1'>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>등록일</th>
				<th>조회수</th>
				<th>상태</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${articles}" var="article">
				<c:if test="${param.categoryNo != 0 && article.categoryNo == param.categoryNo}">
					<tr>
						<td>${article.no}</td>
						<td id='title'>
							<ul id='tags'>
								<c:forEach items="${article.tags}" var="tag">
									<li id='color'
										style="background-color: #${tag.tagColor}; color: #${tag.fontColor};">${tag.name}</li>
								</c:forEach>
							</ul> <a href='detail?no=${article.no}'>${article.title}</a>
						</td>
						<td>${article.writer.nickname}</td>
						<td><fmt:formatDate value="${article.createdDate}"
								pattern="yyyy.MM.dd" /></td>
						<td>${article.viewCount}</td>
						<td>${article.state == 1 ? "" : "삭제된 게시글"}</td>
					</tr>
				</c:if>
				<c:if test="${param.categoryNo == null}">
				  <tr>
            <td>${article.no}</td>
            <td id='title'>
              <ul id='tags'>
                <c:forEach items="${article.tags}" var="tag">
                  <li id='color'
                    style="background-color: #${tag.tagColor}; color: #${tag.fontColor};">${tag.name}</li>
                </c:forEach>
              </ul> <a href='detail?no=${article.no}'>${article.title}</a>
            </td>
            <td>${article.writer.nickname}</td>
            <td><fmt:formatDate value="${article.createdDate}"
                pattern="yyyy.MM.dd" /></td>
            <td>${article.viewCount}</td>
            <td>${article.state == 1 ? "" : "삭제된 게시글"}</td>
          </tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
	<p>
	<form action='${contextPath}?' method='get'>
	   <input type='hidden' name='categoryNo' value='${param.categoryNo}'>
		제목으로 검색  :  <input type=A'text' name='keyword' value=''>
		<button>검색</button>
	</form>
	</p>
	<hr>
	<h2>게시판 통합 상세 검색</h2>
	
	<p>
	<form action='list' method='get'>
		제목: <input type='text' name='keywordTitle'
			value='${keywordTitle != null ? keywordTitle : ""}'><br>
		작성자: <input type='text' name='keywordWriter'
			value='${keywordWriter != null ? keywordWriter : ""}'><br>
		태그: <input type='text' name='keywordTag'
			value='${keywordTag != null ? keywordTag : ""}'><br>
		<button>검색</button>
	</form>
	</p>
	<jsp:include page="/footer.jsp"></jsp:include>
	<script src="../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
