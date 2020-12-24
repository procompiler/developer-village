<%@page import="com.devil.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="appRoot" value="${pageContext.servletContext.contextPath}" />

<nav>
  <ul>
    <li><a href='${appRoot}'><img id="logo-nav" src="${appRoot}/upload/devil.png" /></a></li>
    <div id="sitename">
      <li><span class="main-color">D</span>eveloper</li>
      <li><span class="main-color">V</span>illage</li>
    </div>
    <a href="${appRoot}/app/user/${loginUser.no}">
     <div class="user-card">
      <div class="user-photo">
        <img src="${appRoot}/upload/user/${loginUser.photo}_60x60.jpg"/>
      </div>
        <h3 class="user-name">
          ${loginUser.nickname}
        </h3>
    </div>
    </a>
    <div class="buttons">
      <li><i class="fas fa-user-friends fa-fw"></i><a href='${appRoot}/app/article/list?categoryNo=1'>자유게시판</a></li>
      <li><i class="fas fa-question fa-fw"></i><a href='${appRoot}/app/article/list?categoryNo=2'>질문</a></li>
      <li><i class="far fa-building fa-fw"></i><a href='${appRoot}/app/article/list?categoryNo=3'>채용공고</a></li>
      <li><i class="fas fa-pencil-alt fa-fw"></i><a href='${appRoot}/app/article/list?categoryNo=4'>스터디</a></li>
      </div>
    </div>
    <hr>
    <div class="buttons">
      <li><i class="fas fa-tags fa-fw"></i><a href="${appRoot}/app/tag/list">태그</a></li>
<%--       <li><i class="fas fa-user-friends fa-fw"></i><a href='<%=contextPath%>/app/community/taglist'>커뮤니티</a></li> --%>
      <div id="tag">
        <li><i class="fas fa-hashtag"></i><a href="${appRoot}/app/article/list?tagNo=1">Java</a></li>
        <li><i class="fas fa-hashtag"></i><a href="${appRoot}/app/article/list?tagNo=5">JavaScript</a></li>
        <li><i class="fas fa-hashtag"></i><a href="${appRoot}/app/article/list?tagNo=3">Ruby</a></li>
        <li><i class="fas fa-hashtag"></i><a href="${appRoot}/app/article/list?tagNo=4">Go</a></li>
      </div>
    </div>
  </ul>
</nav>
<header>
  <div style="float: left;">
    <form action="${appRoot}/app/article/list" autocomplete="off">
      <input id="search" type="search" name="keyword" value="${param.keyword}" placeholder="검색어 입력.."
        style="padding-left: 40px;">
    </form>
  </div>
  <div>
    <a class="btn" href="${appRoot}/app/user/form">회원가입</a>
    <a class="btn" href="${appRoot}/app/auth/login">로그인</a>
    <a class="btn" href="${appRoot}/app/article/form">글쓰기</a>
  </div>
</header>

