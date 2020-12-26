<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <jsp:include page="../user/info.jsp"></jsp:include>
	<h2>뱃지리스트</h2>
  <table border='1'>
    <thead>
      <tr>
        <th>뱃지사진</th>
        <th>뱃지이름</th>
        <th>뱃지획득날짜</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${badgeList}" var="b">
        <tr>
            <td><img src="../upload/badge/${b.photo}_160x160.png"></td>
          <td><a href='../detail?no=${b.no}'>${b.name}</a></td>
          <td><fmt:formatDate value="${b.collectDate}"
                pattern="yyyy.MM.dd" /></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
