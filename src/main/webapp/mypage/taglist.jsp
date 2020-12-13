<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>마이페이지</title>
<style>

div.card {
  background-color: #2E3034;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 6px;
}

.tag-color {
  height:25px;
  border-radius: 6px 6px 0px 0px;
}

</style>
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>
	<jsp:include page="/mypage/info"></jsp:include>

	<h2>팔로우하는 태그</h2>
    <div class="row row-cols-1 row-cols-md-3 g-4">
		<c:forEach items="${tags}" var="t">
		<div class="col">
			<div class="card" style="width: 15rem;">
			<div class="tag-color" style="background-color: #${t.tagColor};"></div>
				<div class="card-body">
					<h5 class="card-title"><a href='../tag/detail?no=${t.no}'>#${t.name}</a></h5>
			  <img style="float: right;"src='../upload/tag/${t.photo}_80x80.png'alt='${t.photo}'>
					<p>0개</p>
					<a href="../follow/tag/delete?followeeNo=${t.no}" class="btn btn-outline-danger">언팔로우</a>
				</div>
			</div>
			</div>
		</c:forEach>
		</div>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>
