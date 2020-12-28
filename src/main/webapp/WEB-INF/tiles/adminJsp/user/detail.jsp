<%@page import="com.devil.domain.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
<h1>회원 상세조회</h1>

<div id="detail" class="row col-sm-8">
<p style="font-size: 20px; font-weight: bold">닉네임: ${user.nickname}</p>
<input type='hidden' name='no' value='${user.no}'>
<br>
<a href='../../upload/user/${user.photo}'> <img
	src='../../upload/user/${user.photo}_160x160.jpg' alt='[${user.photo}]' style="border-radius: 90px;"></a>
<br>
<br>
<input type='hidden' name='no' value='${user.no}'>

<div class="mb-4 mt-5">
<p><span class="user-label">이메일:</span> ${user.email}</p>
<p><span class="user-label">이름:</span> ${user.name}</p>

<p>
	<span class="user-label">가입일:</span>
	<fmt:formatDate value="${user.createdDate}" pattern="yyyy-MM-dd" />
</p>

<c:choose>
	<c:when test="${user.loginType == 1}">
		<p>기본 가입회원</p>
	</c:when>
	<c:when test="${user.loginType == 2}">
		<p>구글 가입회원</p>
	</c:when>
	<c:when test="${user.loginType == 3}">
		<p>깃허브 가입회원</p>
	</c:when>
</c:choose>
<p><span class="user-label">소개:</span> ${user.bio}</p>
<p><span class="user-label">기술:</span> ${user.tech}</p>
<p><span class="user-label">개인 홈페이지:</span> ${user.homepageURL}</p>
<p><span class="user-label">깃허브:</span> ${user.githubURL}</p>
<p><span class="user-label">인스타그램:</span> ${user.instarURL}</p>
<p><span class="user-label">트위터:</span> ${user.twitterURL}</p>

<c:choose>
      <c:when test="${user.state == 1}">
        <a class="btn btn-danger"
        href='inactivate?no=${user.no}'>계정 정지하기</a>
      </c:when>
      <c:otherwise>
        <a class="btn btn-outline-danger"
       href='activate?no=${user.no}'>정지/탈퇴계정 복구</a>
      </c:otherwise>
    </c:choose>

<p><a href='list' style='color: blue;'>회원 목록으로</a></p>
</div>
</div>
</div>