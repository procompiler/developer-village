<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<a class='btn btn-primary' href='form'>뱃지 추가</a>
  <table border='1'>
    <thead>
      <tr>
        <th>기준</th>
        <th>카운트</th>
      </tr>
    </thead>
    <tbody>
	    <c:forEach items="${badgeStans}" var="s">
	        <form>
	         <input type="hidden" name="badgeNo" value="${bs.name}"/>
	         <input type="hideen" name="no" value="${}
	        <td>${badge.no}<c:if test="${badge.state == 0}">삭제된 뱃지  입니다.</c:if></td>
	        <td id="name"><a href='detail?no=${badge.no}'>${badge.name}</a></td>
	        <td><img style="width: 40px;" src='../../upload/badge/${badge.photo}_60x60.png'></td>
	        <td>${badge.content}</td>
	      <tr>
	        </form>
	    </c:forEach>
    </tbody>
  </table>
