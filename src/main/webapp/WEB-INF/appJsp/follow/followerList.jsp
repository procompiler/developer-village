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
	<h2>팔로워</h2>
	<div class="row row-cols-1 row-cols-md-3 row-cols-lg-4">
	<c:forEach items="${userList}" var="u">
		<div class="col mb-3">
			<div class="card h-100">
				<div class="mx-auto p-3">
						<a href='../user/detail?no=${u.no}'> <img class="card-img-top rounded-circle"
							src='../../upload/user/${u.photo}_100x100.jpg' alt='[${u.nickname}]' />
						</a>
					</div>
				<div class="card-body mx-auto">
					<h5 class="card-title text-center"><a href='../user/detail?no=${u.no}'>${u.nickname}</a></h5>
					<c:choose>
					<c:when test="${u.followed}">
					<a class='btn btn-outline-danger'href='deleteUser?followeeNo=${u.no}'>언팔로우</a>
					</c:when>
					<c:otherwise>
					<a class='btn btn-primary'href='addUser?followeeNo=${u.no}'>팔로우</a>
					</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</c:forEach>
	</div>
	</div>
<div class="col-2">
</div>
</div>
	<jsp:include page="../footer.jsp"></jsp:include>