<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

  <div class="mini-navi">
  <a href='../article/list' style='text-decoration: none;'>전체글</a>
  <a href='../article/list?categoryNo=1' style='text-decoration: none;'>자유게시판</a>
  <a href='../article/list?categoryNo=2' style='text-decoration: none;'>QnA</a>
  <a href='../article/list?categoryNo=3' style='text-decoration: none;'>채용공고</a>
  <a href='../article/list?categoryNo=4' style='text-decoration: none;'>스터디</a>
  </div>
  <h1>
    <a href='../article/list' style='text-decoration: none;'>게시글 관리</a>
  </h1>

  <div id="search-bar">
  <span style="float: left">제목 검색</span>
  <form action='${contextPath}?' method='get' autocomplete="off">
  <input id="search" name="keyword" type="text" value='${param.keyword}' placeholder="제목 검색" style=" padding-left: 40px; margin-right: 30px;">
  <button class="btn btn-primary">검색</button>
  </form>
  </div>

	<table border='1'>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>카테고리</th>
				<th>작성자</th>
				<th>등록일</th>
				<th>조회수</th>
				<th>댓글수</th>
				<th>상태</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${articles}" var="article">
			<c:if
        test="${param.categoryNo != 0 && article.categoryNo == param.categoryNo}">
				<tr>
					<td>${article.no}</td>
					<td id='title'>
						<ul id='tags'>
						</ul> <a href='${article.no}'>${article.title}</a>
					</td>
					<td><c:choose>
							<c:when test="${article.categoryNo == 1}">
								<p>자유게시판</p>
							</c:when>
							<c:when test="${article.categoryNo == 2}">
								<p>QnA</p>
							</c:when>
							<c:when test="${article.categoryNo == 3}">
								<p>채용공고</p>
							</c:when>
							<c:when test="${article.categoryNo == 4}">
								<p>스터디</p>
							</c:when>
						</c:choose></td>
					<td>${article.writer.nickname}</td>
					<td><fmt:formatDate value="${article.createdDate}"
							pattern="yyyy.MM.dd" /></td>
					<td>${article.viewCount}</td>
					<td>${article.commentCount}</td>
					<td>${article.state == 1 ? "게시" : "미게시"}</td>
				</tr>
				</c:if>
				<c:if test="${param.categoryNo == null}">
				<tr>
          <td>${article.no}</td>
          <td id='title'>
            <ul id='tags'>
            </ul> <a href='${article.no}'>${article.title}</a>
          </td>
          <td><c:choose>
              <c:when test="${article.categoryNo == 1}">
                <p2>자유게시판</p2>
              </c:when>
              <c:when test="${article.categoryNo == 2}">
                <p2>QnA</p2>
              </c:when>
              <c:when test="${article.categoryNo == 3}">
                <p2>채용공고</p2>
              </c:when>
              <c:when test="${article.categoryNo == 4}">
                <p2>스터디</p2>
              </c:when>
            </c:choose></td>
          <td>${article.writer.nickname}</td>
          <td><fmt:formatDate value="${article.createdDate}"
              pattern="yyyy.MM.dd" /></td>
          <td>${article.viewCount}</td>
          <td>${article.commentCount}</td>
          <td>${article.state == 1 ? "게시" : "미게시"}</td>
        </tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
	
<div>
<c:if test="${prevPageNo == currPageNo}">
  [이전]
</c:if>
<c:if test="${prevPageNo < currPageNo}">
  <a href="?keyword=${keyword}&pageNo=${prevPageNo}&pageSize=${pageSize}">[이전]</a>
</c:if>
<span> ${currPageNo} </span>
<c:if test="${nextPageNo == currPageNo}">
  [다음]
</c:if>
<c:if test="${nextPageNo > currPageNo}">
  <a href="?keyword=${keyword}&pageNo=${nextPageNo}&pageSize=${pageSize}">[다음]</a>
</c:if>
</div>

