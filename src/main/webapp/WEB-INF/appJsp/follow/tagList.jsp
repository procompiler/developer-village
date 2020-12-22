<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>마이페이지</title>
<jsp:include page="../header.jsp"></jsp:include>
<jsp:include page="../user/info.jsp"></jsp:include>

<h2>팔로우하는 태그</h2>
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
					<p>0개</p>
					<a href="delete?followeeNo=${t.no}" class="btn btn-outline-danger">언팔로우</a>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
<jsp:include page="../footer.jsp"></jsp:include>