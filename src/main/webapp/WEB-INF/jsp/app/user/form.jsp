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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>
<jsp:include page="/header.jsp"></jsp:include>
</head>
<body>
<div class="row">
  <div class="col-9">
	<h1>회원가입</h1>
	<form action="add" method="POST" enctype="multipart/form-data">
		<div class="mb-3 row">
        <label for="email" class='form-label col-sm-2 col-form-label'>이메일</label>
        <div class="col-sm-10">
          <input type='text' class="form-control" id='email'
            name='email' value='${user.email}'>
        </div>
      </div>
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
          <input type='text' class="form-control" id='name'
            name='name' value='${user.name}'>
        </div>
      </div>
		<div class="mb-3 row">
        <label for="password" class='form-label col-sm-2 col-form-label'>비밀번호</label>
        <div class="col-sm-10">
          <input type='text' class="form-control" id='password'
            name='password' value='${user.password}'>
        </div>
      </div>
		<div class="mb-3 row">
        <label for="homePage" class='form-label col-sm-2 col-form-label'>개인홈페이지</label>
        <div class="col-sm-10">
          <input type='text' class="form-control" id='homePage'
            name='homePage' value='${user.homePage}'>
        </div>
      </div>
      <div class="mb-3 row">
        <label for="loginType" class='form-label col-sm-2 col-form-label'>로그인타입</label>
        <div class="col-sm-10">
          <select name='loginType' class="form-control" value='${user.loginType}'>
            <option value="1">깃허브</option>
            <option value="2">인스타</option>
            <option value="3">트위터</option>
          </select><br>
        </div>
      </div>
		<label>로그인타입 : </label> 
		<select name="loginType">
			<option value="1">깃허브</option>
			<option value="2">인스타</option>
			<option value="3">트위터</option>
		</select><br>
		<button>회원가입 완료</button>
	</form>
 <jsp:include page="/footer.jsp"></jsp:include>
