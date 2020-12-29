<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-9">
	  <h1 style="font-weight: bold; text-align: left;">비밀번호 변경</h1>
		<a href="updateForm?no=${user.no}" style="float:right;"class="btn btn-outline-primary">회원정보 변경</a><br>
			<input type='hidden' name='no' value='${user.no}'>
			<a href='../../upload/user/${user.photo}'>
			<img src='../../upload/user/${user.photo}_160x160.jpg' style="border-radius: 90px"></a><br> 
			
		<form id="updatePwdForm" action='updatePwd' method='post'>
		  <br>
			<input type='hidden' name='no' value='${user.no}'>

			<div class="mb-3 row">
				<label for="nickname" class='form-label col-sm-2 col-form-label'>닉네임</label>
				<div class="col-sm-10">
					<input type='text' class="form-control-plaintext light" id='nickname'
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

      <div class="mb-3 row">
        <button class="btn btn-primary">수정</button>
      </div>
		</form>
	</div>
</div>

<script>
document.querySelector("#updatePwdForm").onsubmit = () => {
  var inputPassword = document.querySelector("#inputPassword");
  var passwordConfirm = document.querySelector("#passwordConfirm");

  if (inputPassword.value.length < 8) {
    alert("비밀번호를 8자 이상 입력하세요!");
    return false;
  } else if (inputPassword.value != passwordConfirm.value) {
    alert("비밀번호가 일치하지 않습니다.");
    return false;
  }
};
</script>

