<%@page import="com.devil.domain.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
  <div class="col-9">
		<h1 style="font-weight: bold; text-align: left;">프로필 수정</h1>
		<a href="updatePwdForm?no=${user.no}" style="float:right;" class="btn btn-outline-primary">비밀번호 변경</a><br>
		
		<form action='updatePhoto' method='post' enctype='multipart/form-data' class="row g-3">
			<input type='hidden' name='no' value='${user.no}'>
			<a href='../../upload/user/${user.photo}'>
			<img src='../../upload/user/${user.photo}_160x160.jpg' style="border-radius: 90px"></a>
			
			<div class="col-8">
		    <input class="form-control-sm" name='photoFile' type="file">
		  </div>
		  <div class="col-2 mb-4">
		    <button class="btn btn-primary">변경</button>
		  </div>
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
					<input type='text' class="form-control-plaintext light" id='name' readonly
						name='name' value='${user.name}'>
				</div>
			</div>
			<div class="mb-3 row">
				<label for="email" class='form-label col-sm-2 col-form-label'>이메일</label>
				<div class="col-sm-10">
					<input type='text' class="form-control-plaintext light" id='email' readonly
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

			<div class="mb-3 row mb-6">
				<label for="twitterURL" class='form-label col-sm-2 col-form-label'>트위터</label>
				<div class="col-sm-10">
					<input type='text' class="form-control" id='twitterURL'
						name='twitterURL' value='${user.twitterURL}'>
				</div>
			</div>
      <div class="mb-3 row">
			  <button class="btn btn-primary">수정</button>
			</div>
		</form>
	</div>
	
	<hr>
	<div class="row">
	 <div id="dangerzone" class="row col-9 mb-5">
	<h1 style="color:red;">Dager Zone</h1>
	<h3>계정 탈퇴</h3>
	<p>계정 탈퇴를 하신다면:</p>
	<ul class="list-disc pl-6">
	<li>계정 탈퇴를 하신다면 더이상 Devil의 서비스를 이용할 수 없게 됩니다.
	<li>운영진에게 문의할 사항이 있다면 아래 링크를 클릭해주세요.
      <ul>
    <li><a href="https://github.com/settings/applications">GitHub profile settings</a></li>
</ul>
    <li>정말로 탈퇴를 원하신다면 아래 회원탈퇴 버튼을 눌러주세요.
   </li>
	</ul>
	<button class="btn btn-danger">회원탈퇴</button>
	</div>
	</div>
</div>

