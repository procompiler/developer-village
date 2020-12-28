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
    </c:when>
    <c:when test="${param.userNo != -1}">
      <div style="text-align: center; margin-bottom: 50px;">
       <img id="logo-nav" src="/developer-village/upload/devil.png" style="width: 120px;"/>
        <p style="margin-top: 30px; margin-bottom: 3rem; font-size: 28px; font-weight: bold;">
        비밀번호 재설정</p>
      </div>
      
      <p style="text-align:center; font-size: 20px; margin-bottom: 1rem;">새로운 비밀번호를 입력해주세요.</p>
    
      <form id="updatePwd" action="searchPwdResult-send" method="post">
        <input id='userNo' type='hidden' name='userNo' value='${param.userNo}'>
        <div class="mb-3 row">
          <label for="password" class='form-label col-sm-2 col-form-label'>비밀번호</label>
          <div class="col-sm-10">
            <input id='inputPassword' type='password' class="form-control" name='password' placeholder="password">
          </div>
        </div>

        <div class="d-grid gap-2">
          <button class="btn btn-primary">비밀번호 재설정</button>
        </div>
      </form>
      
    </c:when>
  </c:choose>
    
  <div style="text-align:center;">
    <a href="searchId">아이디 찾기</a>
    <span> | </span>
    <a href="login">로그인</a>
  </div>
</div>

<script>
  document.querySelector("#updatePwd").onsubmit = () => {
    if (document.querySelector("#inputPassword").value.length < 1 ) {
        alert("입력하지 않은 항목이 있습니다.")
        return false;
      }
    };
</script>
