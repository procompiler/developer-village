<%@page import="java.util.Calendar"%>
<%@page import="com.devil.domain.Block"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
<link rel="stylesheet"
  href="../node_modules/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>
  <jsp:include page="/header.jsp"></jsp:include>
  <a href='../admin/userList' style='text-decoration: none;'>전체회원관리</a>
  <a href='../report/list' style='text-decoration: none;'>신고내역</a>
  <a href='../block/list' style='text-decoration: none;'>활동정지회원</a>
  <h1>
    <a href='list' style='text-decoration: none;'>활동정지회원</a>
  </h1>
  <br>

  <%
    List<Block> list = (List<Block>) request.getAttribute("blockList");
  %>

<%
Calendar blockTermination = Calendar.getInstance();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
%>

  <table border='1'>
    <tr>
      <th>정지회원 이름(이메일)</th>
      <th>차단사유</th>
      <th>해제사유</th>
      <th>처리일</th>
      <th>차단종료일</th>
      <th>상태</th>
    </tr>

    <%
      for (Block block : list) {
        blockTermination.setTime(block.getPermittedDate());
        blockTermination.add(Calendar.DATE, block.getBlockedDates());
    %>

    <tr>
      <td><%=block.getReportedUser().getNickname()%> [<%=block.getReportedUser().getEmail()%>]</td>
      <td><%=block.getBlockedReason()%></td>
      <td><%=block.getUnBlockedReason() == null ? "" : block.getUnBlockedReason()%></td>
      <td><%=formatter.format(block.getPermittedDate())%></td>
      <td><%=formatter.format(blockTermination.getTime())%></td>
    </tr>
    <%
      }
    %>
  </table>
  <jsp:include page="/footer.jsp"></jsp:include>
     <script src="../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
  
</body>
</html>