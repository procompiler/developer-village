<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="py-1">
	<c:choose>
		<c:when test="${user.no != loginUser.no && null ne user}">
			<div class="card text-center user-info mt-5">
				<div class="card-body">
					<img class="card-img-top rounded-circle"
						src='../../upload/user/${user.photo}_100x100.jpg'
						alt='[${user.nickname}]' />
					<c:choose>
						<c:when test="${followed}">
							<a class="btn btn-outline-danger position-absolute top-0 end-0"
								href="../follow/deleteUser?followeeNo=${user.no}">언팔로우</a>
						</c:when>
						<c:otherwise>
							<a class="btn btn-primary position-absolute top-0 end-0"
								href="../follow/addUser?followeeNo=${user.no}">팔로우</a>
						</c:otherwise>
					</c:choose>
					<br>
					<h5 class="card-title">${user.nickname}</h5>
					<a class="card-link" href="#">팔로잉 ${user.followingCount}</a> 
					<a class="card-link" href="#">팔로워 ${user.followerCount}</a>
					<p class="card-text">${user.bio}</p>
					<div class="urls">
						<c:if test="${not empty user.homepageURL}">
							<a class="btn" href="${user.homepageURL}"><i
								class="fas fa-home fa-lg"></i></a>
						</c:if>
						<c:if test="${not empty user.githubURL}">
							<a class="btn" href="${user.githubURL}"><i
								class="fab fa-github-alt fa-lg"></i></a>
						</c:if>
						<c:if test="${not empty user.instarURL}">
							<a class="btn" href="${user.instarURL}"><i
								class="fab fa-instagram fa-lg"></i></a>
						</c:if>
						<c:if test="${not empty user.twitterURL}">
							<a class="btn" href="${user.twitterURL}"><i
								class="fab fa-twitter fa-lg"></i></a>
						</c:if>
					</div>
				</div>
			</div>
			<div class="row mt-4">
        <div class="col-2">
					<div class="btn-group-vertical">
						<a class="btn btn-secondary py-3"
							href="../article/writtenList?no=${user.no}">작성 게시글</a> <a
							class="btn btn-secondary py-3"
							href="../comment/writtenList?no=${user.no}">작성 댓글</a>
					</div>
				<c:if test="${not emptyuser.tech}">
				<br>
				<div class="px-3 mt-3">
        <h6 class="fw-bold"><i class="fas fa-tools"></i> 기술스택</h6>
        <p>
            ${user.tech}
        </p>
        </div>
        </c:if>
				</div>
				<div class="col-7">
		</c:when>

		<c:otherwise>
			<div class="card text-center user-info mt-5">
				<div class="card-body">
					<img class="card-img-top rounded-circle"
						src='../../upload/user/${loginUser.photo}_100x100.jpg'
						alt='[${loginUser.nickname}]' /> <a
						class="btn btn-primary position-absolute top-0 end-0"
						href="../user/updateForm?no=${loginUser.no}">프로필 수정</a> <br>
					<h5 class="card-title">${loginUser.nickname}</h5>
					<a class="card-link" href="../follow/userList">팔로잉
						${loginUser.followingCount}</a> <a class="card-link"
						href="../follow/followerList">팔로워 ${loginUser.followerCount}</a>
					<p class="card-text">${loginUser.bio}</p>
					<div class="urls">
						<c:if test="${not empty loginUser.homepageURL}">
							<a class="btn" href="${loginUser.homepageURL}"><i
								class="fas fa-home fa-lg"></i></a>
						</c:if>
						<c:if test="${not empty loginUser.githubURL}">
							<a class="btn" href="${loginUser.githubURL}"><i
								class="fab fa-github-alt fa-lg"></i></a>
						</c:if>
						<c:if test="${not empty loginUser.instarURL}">
							<a class="btn" href="${loginUser.instarURL}"><i
								class="fab fa-instagram fa-lg"></i></a>
						</c:if>
						<c:if test="${not empty loginUser.twitterURL}">
							<a class="btn" href="${loginUser.twitterURL}"><i
								class="fab fa-twitter fa-lg"></i></a>
						</c:if>
					</div>
				</div>
			</div>
			<div class="row mt-4">
				<div class="col-2">
					<div class="btn-group-vertical float-start">
						<a class="btn btn-secondary py-3"
							href="../article/writtenList?no=${loginUser.no}">작성 게시글</a> <a
							class="btn btn-secondary py-3" href="../article/feed">피드</a> <a
							class="btn btn-secondary py-3"
							href="../comment/writtenList?no=${loginUser.no}">작성 댓글</a> <a
							class="btn btn-secondary py-3" href="../follow/tagList">팔로잉
							태그</a> <a class="btn btn-secondary py-3"
							href="../follow/userList" aria-pressed="true">내가 팔로우하는 유저</a> <a
							class="btn btn-secondary py-3" href="../follow/followerList">나를
							팔로우하는 유저</a> <a class="btn btn-secondary py-3"
							href="../bookmark/list">북마크</a>
					</div>
				</div>
				<div class="col-7">
		</c:otherwise>
	</c:choose>

	<script>
var url = window.location.href;
var btnSecondaries = $(".btn-secondary.py-3");
for (var btn of btnSecondaries) {
if (url.endsWith(btn.getAttribute("href").split('./')[1])) {
	btn.classList.remove("btn-secondary");
	btn.classList.add("btn-primary");
	btn.classList.add("btn-active");
	break;
}
}
</script>