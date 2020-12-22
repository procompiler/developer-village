<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뱃지 목록</title>
  <jsp:include page="../admin-header.jsp"></jsp:include>
  <h1>뱃지 목록</h1>
	<a class='btn btn-primary' href='form'>뱃지 추가</a>
	
  <c:if test="${param.keyword != null}">
  '${param.keyword}'로 검색한 결과입니다.
  </c:if>

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
	        <td>${badge.no}</td>
	        <td id="name"><a href='detail?no=${badge.no}'>${badge.name}</a></td>
	        <td><img style="width: 40px;" src='../../upload/badge/${badge.photo}_60x60.png'></td>
	        <td>${badge.content}</td>
	      <tr>
	    </c:forEach>
    </tbody>
  </table>
  
  <p>
  <form action='list?' method='get'>
    검색어: <input type='text' name='keyword' value=''>
    <button>검색</button>
  </form>
  </p>
  
  <jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>