<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
  <h1>뱃지 목록</h1>
	<a class='btn btn-primary' href='form'>뱃지 추가</a>
	

  <table border='1'>
    <thead>
      <tr>
        <th>번호</th>
        <th>뱃지이름</th>
        <th>뱃지사진</th>
        <th>뱃지내용</th>
      </tr>
    </thead>
    <tbody>
	    <c:forEach items="${list}" var="badge">
	      <tr>
	        <td>${badge.no}<c:if test="${badge.state == 0}">삭제된 뱃지  입니다.</c:if></td>
	        <td id="name"><a href='detail?no=${badge.no}'>${badge.name}</a></td>
	        <td><img style="width: 40px;" src='../../upload/badge/${badge.photo}_60x60.png'></td>
	        <td>${badge.content}</td>
	      <tr>
	    </c:forEach>
    </tbody>
  </table>
  

