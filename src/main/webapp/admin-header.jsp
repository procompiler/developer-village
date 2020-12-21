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
    
    <span class="site-admin-devil">Devil</span>
    
  </div>

<hr>

  <div class="site-admin2">
  <ul>
    <li><a class="btn" href="<%=contextPath%>/admin/user/list">
    <p><i class="fas fa-user-alt"></i></p>회원 관리</a></li>
    <li><a class="btn" href="<%=contextPath%>/admin/badge/list">
    <p><i class="fas fa-cookie-bite"></i></p>뱃지 관리</a></li>
    <li><a class="btn" href="<%=contextPath%>/admin/tag/list">
    <p><i class="fas fa-tag"></i></p>태그 관리</a></li>
    <li><a class="btn" href="<%=contextPath%>/admin/article/form">
    <p><i class="fas fa-chart-pie"></i></p>통계</a></li>
  </ul>  
  </div>
</header>


<div class='container'>