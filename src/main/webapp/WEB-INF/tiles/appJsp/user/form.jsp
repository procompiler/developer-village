<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
<div id="auth">
  <div style="text-align: center; margin-bottom: 50px;">
   <img id="logo-nav" src="/developer-village/upload/devil.png" style="width: 120px;"/>
    <p style="margin-top: 30px; margin-bottom: 3rem; font-size: 28px; font-weight: bold;">
    Devil 회원가입</p>
    </div>

  <p style="text-align:center">
  이메일, 전화번호, 이름은 추후 수정할 수 없습니다.
  </p>
	<form id="userForm" action="add" method="post">
		<div class="mb-3 row">
      <label for="email" class='form-label col-sm-2 col-form-label'>이메일</label>
        <div class="col-sm-10">
          <input id="inputEmail" type='email' class="form-control" name='email' placeholder="Example@test.com">
        </div>
    </div>
    
		<div class="mb-3 row">
      <label for="password" class='form-label col-sm-2 col-form-label'>비밀번호</label>
        <div class="col-sm-10">
          <input id="inputPassword" type='password' class="form-control" name='password' 
                 placeholder="8~20자 이내의 영문, 숫자, 특수문자 중 2가지 이상 포함">
        </div>
    </div>
    
		<div class="mb-3 row">
      <label for="passwordConfirm" class='form-label col-sm-2 col-form-label'>비밀번호 확인</label>
        <div class="col-sm-10">
          <input id="passwordConfirm" type='password' class="form-control" name='passwordConfirm' 
                 placeholder="위의 비밀번호와 동일하게 입력">
        </div>
    </div>
    
    <br><hr><br>
    
		<div class="mb-3 row">
      <label for="name" class='form-label col-sm-2 col-form-label'>이름</label>
        <div class="col-sm-10">
          <input id="inputName" type='text' class="form-control" name='name' placeholder="반드시 실명으로 기입">
        </div>
    </div>

		<div class="mb-3 row">
      <label for="password" class='form-label col-sm-2 col-form-label'>전화번호</label>
        <div class="col-sm-10">
          <input id="inputTel" type='text' class="form-control" name='tel' placeholder="휴대폰번호 입력 ('-' 제외)">
      </div>
    </div>
    
		<div class="mb-3 row">
      <label for="nickname" class='form-label col-sm-2 col-form-label'>닉네임</label>
        <div class="col-sm-10">
          <input id="inputNickname" type='text' class="form-control" name='nickname' placeholder="커뮤니티에서 사용할 별명(최소 4글자)">
        </div>
    </div>
    
    <!-- 디폴트 회원 사진 -->
    <input type='hidden' class="form-control" name='photo' value="fe8a0349-0080-4cc6-85d2-25dc1646441c">

		<div class="d-grid gap-2">
     <button class="btn btn-primary">가입하기</button>
     <button id="btn" type='button' class='btn btn-danger' onclick="location.href='main'">취소</button>
    </div>
	</form>
</div>
<script>
document.querySelector("#userForm").onsubmit = () => {
	var inputEmail = document.querySelector("#inputEmail");
	var inputPassword = document.querySelector("#inputPassword");
	var passwordConfirm = document.querySelector("#passwordConfirm");
	var inputTel = document.querySelector("#inputTel");
	var inputNickname = document.querySelector("#inputNickname");
	var inputName = document.querySelector("#inputName");
	
	if (inputEmail.value.length < 8) {
		alert("이메일을 알맞게 입력했는지 확인하세요!");
		return false;
	}
 
	if (inputPassword.value.length < 8) {
		alert("비밀번호를 8자 이상 입력하세요!");
		return false;
	} else if (inputPassword.value != passwordConfirm.value) {
		alert("비밀번호가 일치하지 않습니다.");
		return false;
	}
	
	if (inputName.value.length < 2) {
		alert("이름을 알맞게 입력했는지 확인하세요!");
		return false;
	}
	
	if (inputTel.value.length < 8) {
		alert("전화번호를 알맞게 입력했는지 확인하세요!");
		return false;
	}
	
	if (inputNickname.value.length < 4) {
		alert("닉네임을 알맞게 입력했는지 확인하세요!");
		return false;
	}
	
	if (document.querySelector("#inputName").value.length < 1 ||
		document.querySelector("#inputTel").value.length < 1 ||
		document.querySelector("#inputEmail").value.length < 1 ||
		document.querySelector("#inputNickname").value.length < 1 ||
		document.querySelector("#inputPassword").value.length < 1) {
	    alert("모든 항목을 입력해야 회원 가입이 가능합니다.");
	    return false;
	}
};
</script>
