<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="auth">
  <c:choose>
    <c:when test="${param.userNo == -1}">
      <div style="text-align: center; margin-bottom: 50px;">
        <img id="logo-nav" src="/developer-village/upload/devil.png" style="width: 120px;"/>
        <p style="margin-top: 30px; margin-bottom: 3rem; font-size: 28px; font-weight: bold;">
        비밀번호 찾기 결과</p>
      </div>
      <p style="text-align:center; margin-bottom: 4rem;">
        <span style="font-size: 20px;">회원 정보를 찾을 수 없습니다.</span><br>
        <span>입력하신 정보가 정확한지 확인하시길 바랍니다.</span>
      </p>
      
      <div style="text-align:center;">
        <a href="#" onClick="history.back()">이전으로</a>
        <span> | </span>
        <a href="../user/form">회원가입</a>
      </div>
      
    </c:when>
    
    <c:when test="${param.userNo != -1}">
      <div style="text-align: center; margin-bottom: 50px;">
       <img id="logo-nav" src="/developer-village/upload/devil.png" style="width: 120px;"/>
        <p style="margin-top: 30px; margin-bottom: 3rem; font-size: 28px; font-weight: bold;">
        비밀번호 재설정</p>
      </div>
      
      <p style="text-align:center; font-size: 20px; margin-bottom: 1rem;">새로운 비밀번호를 입력해주세요.</p>
    
      <form id="updatePwdForm" action="updatePwd" method="post">
        <input id='userNo' type='hidden' name='userNo' value='${param.userNo}'>
        <div class="mb-3 row">
          <label for="password" class='form-label col-sm-2 col-form-label'>비밀번호</label>
          <div class="col-sm-10">
            <input id='inputPassword' type='password' class="form-control" name='password' placeholder="password">
          </div>
        </div>
    
		    <div class="mb-3 row">
		      <label for="passwordConfirm" class='form-label col-sm-2 col-form-label'>비밀번호 확인</label>
		        <div class="col-sm-10">
		          <input id="passwordConfirm" type='password' class="form-control" name='passwordConfirm' 
		                 placeholder="위의 비밀번호와 동일하게 입력">
		        </div>
		    </div>
		    
        <div class="d-grid gap-2">
          <button class="btn btn-primary">비밀번호 재설정</button>
        </div>
      </form>
      
    <div style="text-align:center;">
      <a href="searchId">이메일 계정 찾기</a>
      <span> | </span>
      <a href="login">로그인</a>
    </div>
      
    </c:when>
  </c:choose>

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
