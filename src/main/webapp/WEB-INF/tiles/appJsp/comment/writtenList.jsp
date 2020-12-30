<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="../user/info.jsp"></jsp:include>

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
				<td id='content'><a href='${c.articleNo}'>${c.content}</a>
				</td>
				<td><fmt:formatDate value="${c.createdDate}"
						pattern="yyyy.MM.dd" /></td>
				<td><a class='btn btn-outline-danger'
					href='delete?no=${c.no}'>삭제</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="../user/badgeList.jsp"></jsp:include>

<style>
table {
  border-collapse: separate;
  border-style: hidden;
  margin: 0px 0px auto 0px;
  border-spacing: 0px 6px;
  width: 900px;
  overflow: hidden;
}
</style>