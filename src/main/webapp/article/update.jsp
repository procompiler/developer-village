<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>게시글 수정</title>
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

	<form action='<c:url value='/app/article/update'/>' method='post'>
		<input type='hidden' name='no' value='${article.no}'><br>

		<input type='text' class="form-control" name='title' value='${article.title}'><br>

    <div class="mb-3">
      <label for="exampleFormControlTextarea1" class="form-label"
        ></label>
      <textarea class="form-control" id="exampleFormControlTextarea1" rows="20" name='content'>${article.content}</textarea>
    </div>
    
    <p>
      태그<br>
      <c:forEach items="${tags}" var="tag">
        <input class="form-check-input" type='checkbox' name='tagNo' value='${tag.no}'
          <c:forEach items="${article.tags}" var="articleTag">
          <c:if test="${articleTag.no == tag.no}">
            checked
          </c:if>
          </c:forEach>
        >${tag.name}
      <c:if test="${tag.no % 9== 0 }"><br></c:if>
    </c:forEach>
  </p>
  
		<button class="btn btn-primary">수정</button>
	</form>

	<jsp:include page="/footer.jsp"></jsp:include>

	<script src="../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>