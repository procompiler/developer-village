<%@page import="java.util.ArrayList"%>
<%@page import="com.devil.domain.Tag"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.devil.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
#urls {
	padding: 5px;
	font-size: 2em;
}
</style>
<h1>사용자 정보</h1>
<c:choose>
<c:when test="${user.no != loginUser.no && null ne user}">
	<img src='../../upload/${user.photo}_160x160.jpg'
		alt='[${user.photo}.jpg]'>
	<c:choose>
		<c:when test="${followed}">
			<a class="btn btn-outline-danger"
				href="../follow/deleteUser?followeeNo=${user.no}">언팔로우</a>
		</c:when>
		<c:otherwise>
			<a class="btn btn-primary"
				href="../follow/addUser?followeeNo=${user.no}">팔로우</a>
		</c:otherwise>
	</c:choose>
	<br>
	<p>닉네임: ${user.nickname}</p>
	<p>이메일 ${user.email}</p>
	<p>기술 목록 ${user.tech}</p>
	<p><a href="../follow/userList">팔로잉: ${user.followingCount}</a></p>
	<p><a href="../follow/followerList">팔로워: ${user.followerCount}</a></p>
	<div>
		<c:choose>
			<c:when test="${empty user.homepageURL}">
				<a href="${user.homepageURL}"><i class="fas fa-home"></i></a>
			</c:when>
			<c:when test="${empty user.githubURL}">
				<a href="${user.githubURL}"><i class="fab fa-github-alt"></i></a>
			</c:when>
			<c:when test="${empty user.instarURL}">
				<a href="${user.instarURL}"><i class="fab fa-instagram"></i></a>
			</c:when>
			<c:when test="${empty user.twitterURL}">
				<a href="${user.twitterURL}"><i class="fab fa-twitter"></i></a>
			</c:when>
		</c:choose>
	</div>
	<a class="btn btn-outline-primary"
		href="../article/writtenList?no=${user.no}">작성게시글</a>
	<a class="btn btn-outline-primary"
		href="../comment/writtenList?no=${user.no}">작성댓글</a>
</c:when>


<c:otherwise>
	<img src='../../upload/${user.photo}_160x160.jpg'
		alt='[${user.photo}.jpg]'>
	<br>
	<p>닉네임: ${user.nickname}</p>
	<p>이메일 ${user.email}</p>
	<p>기술 목록 ${user.tech}</p>
	 <p><a href="../follow/userList">팔로잉: ${user.followingCount}</a></p>
  <p><a href="../follow/followerList">팔로워: ${user.followerCount}</a></p>
	<div>
		<c:choose>
			<c:when test="${empty loginUser.homepageURL}">
				<a href="${loginUser.homepageURL}"><i class="fas fa-home"></i></a>
			</c:when>
			<c:when test="${empty loginUser.githubURL}">
				<a href="${loginUser.githubURL}"><i class="fab fa-github-alt"></i></a>
			</c:when>
			<c:when test="${empty loginUser.instarURL}">
				<a href="${loginUser.instarURL}"><i class="fab fa-instagram"></i></a>
			</c:when>
			<c:when test="${empty loginUser.twitterURL}">
				<a href="${loginUser.twitterURL}"><i class="fab fa-twitter"></i></a>
			</c:when>
		</c:choose>
	</div>
	<a class="btn btn-outline-primary" 
	  href="../user/updateForm?no=${loginUser.no}">프로필 수정</a>
	<br>
	<a class="btn btn-outline-primary"
    href="../article/feed">피드</a>
	<a class="btn btn-outline-primary"
		href="../article/writtenList?no=${loginUser.no}">작성게시글</a>
	<a class="btn btn-outline-primary"
		href="../comment/writtenList?no=${loginUser.no}">작성댓글</a>
	<a class="btn btn-outline-primary" href="../follow/tagList">팔로잉 태그</a>
	<a class="btn btn-outline-primary" href="../follow/userList">팔로잉
		유저</a>
	<a class="btn btn-outline-primary" href="../follow/followerList">팔로워</a>
	<a class="btn btn-outline-primary" href="../bookmark/list">북마크</a>
	<a class="btn btn-outline-primary" href="../collect/list">뱃지</a>
</c:otherwise>
</c:choose>