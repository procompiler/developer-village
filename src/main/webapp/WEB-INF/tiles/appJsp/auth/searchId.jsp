<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="auth">
  <div style="text-align: center; margin-bottom: 50px;">
   <img id="logo-nav" src="/developer-village/upload/devil.png" style="width: 120px;"/>
    <p style="margin-top: 30px; margin-bottom: 3rem; font-size: 28px; font-weight: bold;">
    아이디 찾기</p>
    </div>

  <form id="searchId" action="searchId-send" method="post">
    <div class="mb-3 row">
      <label for="name" class='form-label col-sm-2 col-form-label'>이름</label>
      <div class="col-sm-10">
        <input id="inputName" type='text' class="form-control" name='name' placeholder="Name">
      </div>
    </div>
      
    <div class="mb-3 row">
      <label for="password" class='form-label col-sm-2 col-form-label'>전화번호</label>
      <div class="col-sm-10">
        <input id="inputTel" type='text' class="form-control" name='tel' placeholder="Phone Number">
      </div>
    </div>

    <div class="d-grid gap-2">
      <button class="btn btn-primary">아이디 찾기</button>
    </div>
    
    <div style="text-align:center;">
      <a href="searchPwd">비밀번호 찾기</a>
      <span> | </span>
      <a href="../user/form">회원가입</a>
    </div>
  </form>
</div>

<script>
  document.querySelector("#searchId").onsubmit = () => {
    if (document.querySelector("#inputName").value.length < 1 ||
        document.querySelector("#inputTel").value.length < 1 ) {
        alert("입력하지 않은 항목이 있습니다.")
        return false;
      }
    };
</script>