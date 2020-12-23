<%@page import="com.devil.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <meta http-equiv='Refresh' content='1;url=loginUser'>
<% User loginUser = (User) request.getAttribute("loginUser");
 %>
 <% if (loginUser == null) {
 %>
 <p>로그인 된 상태가 아닙니다!</p>
 <%} else { %>
  <p><%=loginUser.getName() %> 님 안녕히 가세요!</p>
<%}%>
