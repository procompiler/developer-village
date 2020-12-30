<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
<div id="auth">
  <div style="text-align: center; margin-bottom: 50px;">
   <img id="logo-nav" src="/developer-village/upload/devil.png" style="width: 120px;"/>
    <p style="margin-top: 30px; margin-bottom: 3rem; font-size: 28px; font-weight: bold;">
    회원가입 오류</p>
    </div>

  <p style="text-align:center; margin-bottom: 4rem;">
    <span style="font-size: 20px;">일시적인 오류로 회원가입을 완료하지 못했습니다.</span><br>
    <span>잠시 후 다시 시도해주시길 바랍니다.</span>
  </p>
  
  <div style="text-align:center;">
    <a href="form">회원가입</a>
    <span> | </span>
    <a href="../auth/login">로그인</a>
  </div>
</div>

