<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
  
  <div class="row">
  <h1>
    <c:choose>
      <c:when test="${article.categoryNo == 1}">
        <p>자유게시판</p>
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
  
<div>
  <input type='hidden' name='no' value='${article.no}'>
  <br>

  <ul id='tags'>
    <c:forEach items="${tags}" var="tag">
      <span>#${tag.name}</span>
    </c:forEach>
  </ul>

  <div style="width: 60rem; background-color: #37393E;">
    <div class="card-body">
      <h2 class="card-title">${article.title}</h2>
      <p class="card-text">
        등록일: <fmt:formatDate value="${article.createdDate}" pattern="yyyy.MM.dd. HH:mm" />
      </p>
      <p class="card-text">
          작성자: <a href='../user/${article.writer.no}'>${article.writer.nickname}</a></p>
      <p class="card-text">조회수: ${article.viewCount}</p>
      <p class="card-text">상태: ${article.state == 1 ? "게시" : "미게시"}</p>
      <hr>
      <p class="card-text">
        <br><br>
        <pre>${article.content}</pre>
        <br><br>
      </p>
    </div>
  </div>
</div>
<br>
  <a class="btn btn-primary" href='<c:url value='list'/>'>목록</a>
  
      <c:choose>
      <c:when test="${article.state == 1}">
        <a class="btn btn-danger"
        href='inactivate?no=${article.no}'>미게시</a>
      </c:when>
      <c:otherwise>
        <a class="btn btn-outline-danger"
       href='activate?no=${article.no}'>게시</a>
      </c:otherwise>
    </c:choose>
 <br>
 <hr>
 </div>
  <jsp:include page="../comment/list.jsp"></jsp:include>
