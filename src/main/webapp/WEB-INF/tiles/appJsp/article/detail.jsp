<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
      <li id='color' style="background-color: ${tag.tagColor}">${tag.name}</li>
    </c:forEach>
  </ul>

  <div style="width: 80rem; background-color: #37393E;">
    <div class="card-body">
      <h2 class="card-title">${article.title}</h2>
      <h6 class="card-subtitle mb-2 text-muted">
        <fmt:formatDate value="${article.createdDate}" pattern="yyyy.MM.dd. HH:mm" />
      </h6>
      <br>
      <p class="card-text">
        <img src='../../upload/user/${article.writer.photo}_40x40.jpg'
          style='border-radius: 70px' alt='[${article.writer.photo}_40x40]'><br>
          <a href='../user/${article.writer.no}'><span style="font-weight:bold; font-size:105%;">${article.writer.nickname}</span></a></p>
                          
      <p>
      <a class="card-text text-end" href="#comment"><i class="fas fa-comment"></i> 댓글 ${article.commentCount}</a>
      <span class="card-text text-end"><i class="fas fa-eye"></i> 조회 ${article.viewCount}</span>
      </p>
      <hr>
      <p style="float: right;">
        <c:choose>
          <c:when test="${bookmarked == true}">
            <a class="btn btn-outline-danger"
               href="../bookmark/delete?articleNo=${article.no}">북마크취소</a>
          </c:when>
          <c:otherwise>
            <a class="btn btn-primary"
               href="../bookmark/add?articleNo=${article.no}">북마크</a>
          </c:otherwise>
        </c:choose>
      </p>
      <p class="card-text">
        <br><br>
        <pre><span style="font-size:115%">${article.content}</span></pre>
        <br><br>
      </p>
    </div>
  </div>
<br>
  <c:if test="${article.writer.no == loginUser.no}">
	<p style="float: right;">
	  <br>
	  <a class="btn btn-primary" href='<c:url value='update?no=${article.no}'/>'>수정</a>
	  <a class="btn btn-primary" href='delete?no=${article.no}'>삭제</a>
	</p>
	</c:if>
	  <a class="btn btn-primary" href='<c:url value='detail?no=${article.no-1}'/>'>이전글</a>
	  <a class="btn btn-primary" href='<c:url value='detail?no=${article.no+1}'/>'>다음글</a>
	  <a class="btn btn-primary" href='<c:url value='list?categoryNo=${article.categoryNo}'/>'>목록</a>
	  <a class="btn btn-danger"
	    href='../report/reportArticle?no=${article.no}'>신고</a>
	
</div>

  <span id="comment">
    <jsp:include page="../comment/list.jsp"></jsp:include>
  </span>
  
  
