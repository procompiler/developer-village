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
      <label for="password" class='form-label col-sm-2 col-form-label'>전화번호</label>
        <div class="col-sm-10">
          <input id="inputTel" type='text' class="form-control" name='tel' placeholder="휴대폰번호 입력 ('-' 제외)">
      </div>
    </div>
    
		<div class="mb-3 row">
      <label for="nickname" class='form-label col-sm-2 col-form-label'>닉네임</label>
        <div class="col-sm-10">
          <input id="inputNickname" type='text' class="form-control" name='nickname' placeholder="커뮤니티에서 사용할 별명">
        </div>
    </div>
    
		<div class="mb-3 row">
      <label for="name" class='form-label col-sm-2 col-form-label'>이름</label>
        <div class="col-sm-10">
          <input id="inputName" type='text' class="form-control" name='name' placeholder="반드시 실명으로 기입">
        </div>
    </div>
    
    <!-- 디폴트 회원 사진 -->
    <input type='hidden' class="form-control" name='photo' value="fe8a0349-0080-4cc6-85d2-25dc1646441c">

		<div class="d-grid gap-2">
     <button class="btn btn-primary">가입하기</button>
     <button type='button' class='btn btn-danger' onclick="location.href='main'">취소</button>
    </div>
	</form>
</div>

<script>
  document.querySelector("#userForm").onsubmit = () => {
	  if (document.querySelector("#inputName").value.length < 4 ||
				document.querySelector("#inputTel").value.length < 5 ||
				document.querySelector("#inputEmail").value.length < 8 ||
				document.querySelector("#inputNickname").value.length < 5 ||
				document.querySelector("#inputPassword").value.length < 7) {
				alert("입력하지 않은 항목이 있습니다.")
			  return false;
			}
		};
</script>
