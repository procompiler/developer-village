<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../user/info.jsp"></jsp:include>
<h2>알림</h2>
<div class="d-grid gap-3" />
<c:forEach items="${notificationList}" var="n">
	<a href="../${n.url}">
		<div class="card">
			<div class="row g-0">
				<div class="col-sm-2">
					<div class="text-center mr-2 mt-4">
						<img class="card-img-top rounded-circle" alt='[${n.photo}]'
							src='../../upload/user/${n.photo}_60x60.jpg' />
					</div>
				</div>
				<div class="col-sm-10">
					<div class="card-body">
						<p class="card-text">${n.content}</p>
						<p class="card-text">
							<small class="text-muted">${n.difTime}</small>
						</p>
					</div>
				</div>
			</div>
		</div>
</c:forEach>
</div>
</div>
</div>
<div class="col-3"></div>
</div>
