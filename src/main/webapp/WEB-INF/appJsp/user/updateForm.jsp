<%@page import="com.devil.domain.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 수정</title>
<jsp:include page="/header.jsp"></jsp:include>

<div class="row">
	<div class="col-9">
		<h2>프로필 수정</h2>
		<form action='updatePhoto' method='post' enctype='multipart/form-data'>
			<input type='hidden' name='no' value='${user.no}'><br>
			<a href='../../upload/${user.photo}'>
			<img src='../../upload/${user.photo}_160x160.jpg'></a><br> 
				<input type='file' name='photoFile'>
			<button class="btn btn-primary">변경</button>
		</form>
		<form action='update' method='post'>
			<input type='hidden' name='no' value='${user.no}'>
			<div class="mb-3 row">
				<label for="nickname" class='form-label col-sm-2 col-form-label'>닉네임</label>
				<div class="col-sm-10">
					<input type='text' class="form-control" id='nickname'
						name='nickname' value='${user.nickname}'>
				</div>
			</div>
			<div class="mb-3 row">
				<label for="name" class='form-label col-sm-2 col-form-label'>이름</label>
				<div class="col-sm-10">
					<input type='text' class="form-control-plaintext light" id='name'
						name='name' value='${user.name}'>
				</div>
			</div>
			<div class="mb-3 row">
				<label for="email" class='form-label col-sm-2 col-form-label'>이메일</label>
				<div class="col-sm-10">
					<input type='text' class="form-control-plaintext light" id='email'
						name='email' value='${user.email}' readonly>
				</div>
			</div>

			<div class="mb-3 row">
				<label for="bio" class="form-label col-sm-2 col-form-label">소개</label>
				<div class="col-sm-10">
					<textarea class="form-control" id="bio" rows="3" name='bio'>${user.bio}</textarea>
				</div>
			</div>

			<div class="mb-3 row">
				<label for="tech" class="form-label col-sm-2 col-form-label">기술</label>
				<div class="col-sm-10">
					<textarea class="form-control" id="tech" rows="3" name='tech'>${user.tech}</textarea>
				</div>
			</div>

			<div class="mb-3 row">
				<label for="homepageURL" class='form-label col-sm-2 col-form-label'>개인
					홈페이지</label>
				<div class="col-sm-10">
					<input type='text' class="form-control" id='homepageURL'
						name='homepageURL' value='${user.homepageURL}'>
				</div>
			</div>

			<div class="mb-3 row">
				<label for="githubURL" class='form-label col-sm-2 col-form-label'>깃허브</label>
				<div class="col-sm-10">
					<input type='text' class="form-control" id='githubURL'
						name='githubURL' value='${user.githubURL}'>
				</div>
			</div>

			<div class="mb-3 row">
				<label for="instarURL" class='form-label col-sm-2 col-form-label'>인스타</label>
				<div class="col-sm-10">
					<input type='text' class="form-control" id='instarURL'
						name='instarURL' value='${user.instarURL}'>
				</div>
			</div>

			<div class="mb-3 row">
				<label for="twitterURL" class='form-label col-sm-2 col-form-label'>트위터</label>
				<div class="col-sm-10">
					<input type='text' class="form-control" id='twitterURL'
						name='twitterURL' value='${user.twitterURL}'>
				</div>
			</div>

			<button class="btn btn-primary">수정</button>
		</form>
	</div>
</div>

<jsp:include page="/footer.jsp"></jsp:include>