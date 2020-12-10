<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  <%@page import="com.devil.domain.User"%>
  <%@page import="com.devil.domain.Article"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>게시글 조회</title>
<link rel="stylesheet" type="text/css" href='../style.css'>
<link rel="stylesheet"
  href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
  integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
  crossorigin="anonymous" />
  
  <link rel="stylesheet"
  href="../node_modules/bootstrap/dist/css/bootstrap.min.css">
</head>

<body>
  <jsp:include page="/header.jsp"></jsp:include>
  <h1>
    <c:choose>
      <c:when test="${article.categoryNo == 1}">
        <p>커뮤니티</p>
      </c:when>
      <c:when test="${article.categoryNo == 2}">
        <p>QnA</p>
      </c:when>
      <c:when test="${article.categoryNo == 3}">
        <p>채용공고</p>
      </c:when>
      <c:otherwise>
        <p>스터디</p>
      </c:otherwise>
    </c:choose>
  </h1>

  <form action='update' method='post'>
    <input type='hidden' name='no' value='${article.no}'><br>
    <input style="font-size: 20x;" type='text'
      name='title' value='${article.title}' size='50'><br>
      
    <p>
      작성자: <img src='../upload/user/${article.writer.photo}_40x40.jpg'
        style='border-radius: 70px' alt='[${article.writer.photo}_40x40]'>${article.writer.nickname}</p>

    <ul id='tags'>
      <c:forEach items="${tags}" var="tag">
        <li id='color' style="background-color: #${tag.tagColor}; color: #${tag.fontColor};">${tag.name}</li>
      </c:forEach>
    </ul>

    <p>
      등록일:
      <fmt:formatDate value="${article.createdDate}" pattern="yyyy.MM.dd" />
    </p>
    <p>조회수: ${article.viewCount}</p>

    <textarea name='content'>${article.content}</textarea>
    <br>
    <button>수정</button>
  </form>
    <a class='btn btn-hollow' href='delete?no=${article.no}'>삭제</a>
    <a class='btn btn-hollow' href='../report/reportArticle?no=${article.no}'>신고</a>
      
 <%
    boolean bookmarked = (Boolean)request.getAttribute("bookmarked");
  %>
     <a class="btn <%=bookmarked ? "btn-hollow" : ""%>"
        href="../bookmark/<%=bookmarked ? "delete" : "add"%>?articleNo=${article.no}">
        <%=bookmarked ? "북마크취소" : "북마크"%></a>
            
<<<<<<< HEAD
  <jsp:include page="/comment/list?no=${article.no}"></jsp:include>
  <jsp:include page="/footer.jsp"></jsp:include>

=======
	<jsp:include page="/comment/list?no=${article.no}"></jsp:include>
	<jsp:include page="/footer.jsp"></jsp:include>
<script src="../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
>>>>>>> 2ed1e9c45a03ec68707a3d024fbaf281306906be
</body>
</html>