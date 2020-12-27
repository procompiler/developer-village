<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="auth">
  <div style="text-align: center; margin-bottom: 50px;">
    <img id="logo-nav" src="/developer-village/upload/devil.png" style="width: 120px;"/>
    <p style="margin-top: 30px; margin-bottom: 3rem; font-size: 28px; font-weight: bold;">
    아이디 찾기 결과</p>
  </div>
  
  <c:choose>
    <c:when test="${param.userId == ''}">
    <p style="text-align:center; margin-bottom: 4rem;">
      <span style="font-size: 23px;">
      회원 정보를 찾을 수 없습니다.</span><br>
      <span>입력하신 정보를 확인하시길 바랍니다.</span>
    </p>
    </c:when>
    <c:when test="${param.userId != null}">
	    <p style="text-align:center;">
	    회원님의 아이디는<br> 
	    <span style="font-size: 23px; font-weight: bold;">[${param.userId}]</span> 입니다.
	    </p>
    </c:when>
  </c:choose>
  
  <div style="text-align:center;">
    <a href="searchPwd">비밀번호 찾기</a>
    <span> | </span>
    <a href="login">로그인</a>
  </div>
    
</div>
