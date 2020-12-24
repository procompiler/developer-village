<%@page import="com.devil.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="appRoot" value="${pageContext.servletContext.contextPath}" />

<header>
  <div class="site-admin">
  <i class="fas fa-cogs"></i>
    사이트 관리
    
    <span class="site-admin-devil">Devil</span>
    
  </div>

<hr>

  <div class="site-admin2">
  <ul>
    <li><a class="btn" href="${appRoot}/admin/user/list">
    <p><i class="fas fa-user-alt"></i></p>회원 관리</a></li>
    <li><a class="btn" href="${appRoot}/admin/badge/list">
    <p><i class="fas fa-cookie-bite"></i></p>뱃지 관리</a></li>
    <li><a class="btn" href="${appRoot}/admin/tag/list">
    <p><i class="fas fa-tag"></i></p>태그 관리</a></li>
    <li><a class="btn" href="${appRoot}/admin/article/list">
    <p><i class="fas fa-tools"></i></p>게시글 관리</a></li>
  </ul>  
  </div>
</header>


