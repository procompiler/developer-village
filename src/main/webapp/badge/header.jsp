<%@page import="com.devil.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%
String contextPath = request.getServletContext().getContextPath();
User user = (User) session.getAttribute("loginUser");
%>

<link rel="stylesheet" href="<%=contextPath%>/node_modules/@fortawesome/fontawesome-free/css/all.css"/>
<link rel="stylesheet"
  href="<%=contextPath%>/node_modules/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href='<%=contextPath%>/style.css'>

</head>
<body>

<header>
  <div style="float: left;">
    <form autocomplete="off">
    </form>
  </div>
  <div>
    <a class="btn" href="<%=contextPath%>/app/user/form">회원가입</a>
    <a class="btn" href="<%=contextPath%>/app/admin/userList" target="_blank">관리자 페이지</a>
    <a class="btn" href="<%=contextPath%>/app/auth/login">로그인</a>
    <a class="btn" href="<%=contextPath%>/app/article/form">글쓰기</a>
  </div>
</header>



<div class='container'>