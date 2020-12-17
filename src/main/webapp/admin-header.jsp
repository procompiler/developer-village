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
<link rel="stylesheet" type="text/css" href='<%=contextPath%>/admin-style.css'>

</head>
<body>

<header>
  <div class="site-admin">
  <i class="fas fa-cogs"></i>
    사이트 관리
    devil
    
  </div>

<hr>

  <div>
    <i class="fas fa-user-alt"></i><a class="btn" href="<%=contextPath%>/app/user/form.html">회원 관리</a>
    <i class="fas fa-cookie-bite"></i><a class="btn" href="<%=contextPath%>/app/admin/userList">뱃지 관리</a>
    <i class="fas fa-tag"></i><a class="btn" href="<%=contextPath%>/app/auth/login">태그 관리</a>
    <i class="fas fa-chart-pie"></i><a class="btn" href="<%=contextPath%>/app/article/form">통계</a>
  </div>
</header>


<div class='container'>