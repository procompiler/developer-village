<%@page import="java.util.Calendar"%>
<%@page import="com.devil.domain.Block"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
  
  <div class="mini-navi">
  <a href='../user/list' style='text-decoration: none;'>전체 회원관리</a>
  <a href='../report/list' style='text-decoration: none;'>신고내역</a>
  <a href='../block/list' style='text-decoration: none;'>활동정지회원</a>
  </div>
  <h1>
    <a href='../user/list' style='text-decoration: none;'>활동정지회원</a>
  </h1>

  <%
    List<Block> blockList = (List<Block>) request.getAttribute("blockList");
  %>

<%
Calendar blockTermination = Calendar.getInstance();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
%>

  <p style="font-weight: bold">총 활동정지 회원수 <span class="main-color"><%=blockList.size()%></span>명</p>

  <table border='1'>
  <thead>
    <tr>
      <th>정지회원 이름(이메일)</th>
      <th>차단사유</th>
      <th>해제사유</th>
      <th>처리일</th>
      <th>차단종료일</th>
      <th>상태</th>
    </tr>
    </thead>
     <tbody>
    <%
      for (Block block : blockList) {
        blockTermination.setTime(block.getPermittedDate());
        blockTermination.add(Calendar.DATE, block.getBlockedDates());
    %>

    <tr>
      <td id="profile"><%=block.getReportedUser().getNickname()%> [<%=block.getReportedUser().getEmail()%>]</td>
      <td><%=block.getBlockedReason()%></td>
      <td><%=block.getUnBlockedReason() == null ? "" : block.getUnBlockedReason()%></td>
      <td><%=formatter.format(block.getPermittedDate())%></td>
      <td><%=formatter.format(blockTermination.getTime())%></td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>
