<%@page import="com.devil.domain.Block"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
	

	<h1>차단 알림</h1>
<%
  Block block = (Block)request.getAttribute("blockedUser");
%>

<%
Calendar blockTermination = Calendar.getInstance();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
%>

	<h3>현재 차단중입니다.
	차단 만료 후 사이트를 이용하실 수 있습니다.
	</h3>

  <p>차단 사유 : </p>
  <p>${blockedUser.blockedReason}</p>

   <%
   blockTermination.setTime(block.getPermittedDate());
   blockTermination.add(Calendar.DATE, block.getBlockedDates());
   %>
  <p>차단 기한 : </p>
  <p><%=formatter.format(blockTermination.getTime())%>
  </p>