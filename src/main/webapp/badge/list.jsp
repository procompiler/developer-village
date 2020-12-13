<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뱃지 목록</title>
<link rel="stylesheet" type="text/css" href='../style.css'>
<link rel="stylesheet"
  href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
  integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
  crossorigin="anonymous" />
</head>
  <jsp:include page="/header.jsp"></jsp:include>
  <h1>뱃지 목록</h1>
  <button type='button'
      onclick="location.href='${pageContext.request.contextPath}/badge/form.jsp'">뱃지추가</button>
	<button type='button' class='btn btn-primary'
	onclick="location.href='form'">뱃지 추가</button>
	
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
	        <td><img style="width: 40px;" src="../upload/badge/${badge.photo}_160x160.png"></td>
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
  
  <jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>