<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>태그목록</title>
<jsp:include page="../header.jsp"></jsp:include>
</head>
<body>
<h1>태그 목록</h1>
<a class='btn btn-primary' href='form'>태그 추가</a>
<form action='${contextPath}?' method='get'>
	<input type='text' class="col-sm-3" name='keyword' value=''
		placeholder="태그 검색">
	<button class="btn btn-primary">검색</button>
	<c:if test="${param.keyword != null}">
  '${param.keyword}'(으)로 검색한 결과입니다.
  </c:if>
</form>
<div class="row row-cols-1 row-cols-md-3 g-4">
	<c:forEach items="${tagList}" var="t">
		<div class="col">
			<div class="card" style="width: 15rem;">
				<div class="card-band" style="background-color: ${t.tagColor}"></div>
				<div class="card-body">
					<h5 class="card-title">
						<a href='detail?no=${t.no}'>#${t.name}</a>
					</h5>
					<img style="float: right;"
						src='../../upload/tag/${t.photo}_80x80.png' alt='${t.name}'>
					<c:if test="${t.state == 0}">
						<p>삭제됨</p>
					</c:if>
					<c:choose>
						<c:when test="${t.followed}">
							<a class="btn btn-outline-danger"
								href="../follow/deleteTag?followeeNo=${t.no}">언팔로우</a>
						</c:when>
						<c:otherwise>
							<a class="btn btn-primary"
								href="../follow/addTag?followeeNo=${t.no}">팔로우</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
