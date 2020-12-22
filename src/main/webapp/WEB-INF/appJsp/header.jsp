<%@page import="com.devil.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%
String contextPath = request.getServletContext().getContextPath();
%>

<link rel="stylesheet" href="<%=contextPath%>/node_modules/@fortawesome/fontawesome-free/css/all.css"/>
<link rel="stylesheet" type="text/css" href='<%=contextPath%>/custom.css'>
<link rel="stylesheet" type="text/css" href='<%=contextPath%>/style.css'>

</head>
<body>
<nav>
  <ul>
    <li><a href='<%=contextPath%>'><img id="logo-nav" src="<%=contextPath%>/upload/devil.png" /></a></li>
    <div id="sitename">
      <li><span class="main-font-color">D</span>eveloper</li>
      <li><span class="main-font-color">V</span>illage</li>
    </div>
    <a href="<%=contextPath%>/app/user/detail?no=${loginUser.no}">
     <div class="user-card">
      <div class="user-photo">
        <img src="<%=contextPath%>/upload/user/${loginUser.photo}_60x60.jpg"/>
      </div>
        <h3 class="user-name">
          ${loginUser.nickname}
        </h3>
    </div>
    </a>
    <div class="buttons">
      <li><i class="fas fa-user-friends fa-fw"></i><a href='<%=contextPath%>/app/article/list?categoryNo=1'>자유게시판</a></li>
      <li><i class="fas fa-question fa-fw"></i><a href='<%=contextPath%>/app/article/list?categoryNo=2'>질문</a></li>
      <li><i class="far fa-building fa-fw"></i><a href='<%=contextPath%>/app/article/list?categoryNo=3'>채용공고</a></li>
      <li><i class="fas fa-pencil-alt fa-fw"></i><a href='<%=contextPath%>/app/article/list?categoryNo=4'>스터디</a></li>
      </div>
    </div>
    <hr>
    <div class="buttons">
      <li><i class="fas fa-tags fa-fw"></i><a href="<%=contextPath%>/app/tag/list">태그</a></li>
<%--       <li><i class="fas fa-user-friends fa-fw"></i><a href='<%=contextPath%>/app/community/taglist'>커뮤니티</a></li> --%>
      <div id="tag">
        <li><i class="fas fa-hashtag"></i><a href="<%=contextPath%>/app/article/list?tagNo=1">Java</a></li>
        <li><i class="fas fa-hashtag"></i><a href="<%=contextPath%>/app/article/list?tagNo=5">JavaScript</a></li>
        <li><i class="fas fa-hashtag"></i><a href="<%=contextPath%>/app/article/list?tagNo=3">Ruby</a></li>
        <li><i class="fas fa-hashtag"></i><a href="<%=contextPath%>/app/article/list?tagNo=4">Go</a></li>
      </div>
    </div>
  </ul>
</nav>
<header>
  <div style="float: left;">
    <form autocomplete="off">
      <input id="search" type="search" placeholder="검색어 입력.."
        style="padding-left: 40px;">
    </form>
  </div>
  <div>
    <a class="btn" href="<%=contextPath%>/app/user/form">회원가입</a>
    <a class="btn" href="<%=contextPath%>/app/auth/login">로그인</a>
    <a class="btn" href="<%=contextPath%>/app/article/form">글쓰기</a>
  </div>
</header>


<div class='container'>