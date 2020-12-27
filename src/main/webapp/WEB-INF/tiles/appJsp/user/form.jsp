<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="login">
  <div style="text-align: center; margin-bottom: 50px;">
   <img id="logo-nav" src="/developer-village/upload/devil.png" style="width: 120px;"/>
    <p style="margin-top: 30px; margin-bottom: 3rem; font-size: 28px; font-weight: bold;">
    Devil 회원가입</p>
    </div>

	<form action="add" method="post">
		<div class="mb-3 row">
        <label for="email" class='form-label col-sm-2 col-form-label'>이메일</label>
        <div class="col-sm-10">
          <input type='email' class="form-control" name='email' placeholder="Example@test.com">
        </div>
      </div>
		<div class="mb-3 row">
        <label for="password" class='form-label col-sm-2 col-form-label'>비밀번호</label>
        <div class="col-sm-10">
          <input type='password' class="form-control" name='password' placeholder="Password">
        </div>
      </div>
		<div class="mb-3 row">
        <label for="password" class='form-label col-sm-2 col-form-label'>전화번호</label>
        <div class="col-sm-10">
          <input type='text' class="form-control" name='tel' placeholder="Phone Number">
        </div>
    </div>
		<div class="mb-3 row">
        <label for="nickname" class='form-label col-sm-2 col-form-label'>닉네임</label>
        <div class="col-sm-10">
          <input type='text' class="form-control" name='nickname' placeholder="nickname">
        </div>
      </div>
		<div class="mb-3 row">
        <label for="name" class='form-label col-sm-2 col-form-label'>이름</label>
        <div class="col-sm-10">
          <input type='text' class="form-control" name='name' placeholder="Name">
        </div>
      </div>

		<div class="d-grid gap-2">
     <button class="btn btn-primary">회원가입 완료</button>
     <button type='button' class='btn btn-danger' onclick="location.href='main'">취소</button>
    </div>
	</form>
 </div>