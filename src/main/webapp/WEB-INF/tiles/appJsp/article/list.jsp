<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h1 style="margin-left: 70px; font-weight: bold">
	<c:choose>
		<c:when test="${param.categoryNo == 1}">
			<p>자유게시판</p>
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
		<c:when test="${tag != null}">
			<p># ${tag.name}</p>
		</c:when>
		<c:otherwise>
			<p>전체 게시글</p>
		</c:otherwise>
	</c:choose>
</h1>

<form action='${contextPath}?' method='get' autocomplete="off">
	<select id="condition" name="condition">
		<option value="1">제목</option>
		<option value="2">작성자</option>
		<option value="3">태그</option>
	</select> <input type='hidden' name='categoryNo' value='${param.categoryNo}'>
	<input type='text' id="article" name='keyword' value=''
		placeholder="게시판 내 검색">
</form>

<a class="btn2 btn-primary" href="form">글쓰기</a>

<c:if test="${param.keyword != null}">
	<span style="margin-left: 70px;">'${param.keyword}'로 검색한 결과입니다.</span>
</c:if>

<table border='1'>
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>댓글수</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${articles}" var="article">
			<c:if
				test="${param.categoryNo != 0 && article.categoryNo == param.categoryNo && article.state == 1}">
				<tr>
					<td>${article.no}</td>
					<td id='title'>
						<ul id='tags'>
							<c:forEach items="${article.tags}" var="tag">
								<li id='color'
									style="background-color: ${tag.tagColor}; color: ${tag.fontColor};">${tag.name}</li>
							</c:forEach>
						</ul> <a href='detail?no=${article.no}'>${article.title}</a><span
						style="color: #6C5DDF;"></span>
					</td>
					<td><i class="fas fa-comment"></i> ${article.commentCount}</td>
					<td>${article.writer.nickname}</td>
					<td><fmt:formatDate value="${article.createdDate}"
							pattern="yyyy.MM.dd" /></td>
					<td>${article.viewCount}</td>
				</tr>
			</c:if>
			<c:if test="${param.categoryNo == null}">
				<tr>
					<td>${article.no}</td>
					<td id='title'>
						<ul id='tags'>
							<c:forEach items="${article.tags}" var="tag">
								<li id='color'
									style="background-color: ${tag.tagColor}; color: ${tag.fontColor};">${tag.name}</li>
							</c:forEach>
						</ul> <a href='detail?no=${article.no}'>${article.title}</a>
					</td>
					<td>${article.writer.nickname}</td>
					<td><fmt:formatDate value="${article.createdDate}"
							pattern="yyyy.MM.dd" /></td>
					<td>${article.viewCount}</td>
					<td>${article.commentCount}</td>
				</tr>
			</c:if>
		</c:forEach>
	</tbody>
</table>
