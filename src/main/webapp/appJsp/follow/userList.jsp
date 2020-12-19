<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>마이페이지</title>
<jsp:include page="/header.jsp"></jsp:include>
<jsp:include page="/appJsp/user/info.jsp"></jsp:include>
<h2>팔로우하는 유저</h2>
<div class="row row-cols-1 row-cols-md-4 row-cols-lg-5">
	<c:forEach items="${userList}" var="u">
		<div class="col">
			<div class="card h-100">
				 <div class="mx-auto p-3"><a href='../user/detail?no=${u.no}'><img class="card-img-top"
					src='../../upload/user/${u.photo}_80x80.jpg'
					style='border-radius: 70px' alt='[${u.nickname}]'/></a></div>
					<div class="card-body mx-auto">
						<h5 class="card-title text-center">${u.nickname}</h5>
						<a class='btn btn-outline-danger' href='deleteUser?followeeNo=${u.no}'>언팔로우</a>
					</div>
				</a>
			</div>
		</div>
	</c:forEach>
</div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>
