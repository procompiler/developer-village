<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="auth">
  <div style="text-align: center; margin-bottom: 50px;">
   <img id="logo-nav" src="/developer-village/upload/devil.png" style="width: 120px;"/>
    <p style="margin-top: 30px; margin-bottom: 3rem; font-size: 28px; font-weight: bold;">
    게시글 조회 오류</p>
  </div>

  <p style="text-align:center; margin-bottom: 4rem;">
    <span style="font-size: 20px;">게시글을 조회할 수 없습니다.</span><br>
    <span>접근 권한이 없는 게시글이거나, 존재하지 않는 게시글입니다.</span>
  </p>
      
  <div style="text-align:center;">
    <a href="#" onClick="history.back()">이전으로</a>
    <span>  |  </span>
    <a href="../main">메인으로</a>
  </div>
</div>

