<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<div class="row">
<div id="detail" class="row col-sm-8 mb-4 mt-5">
<h3>Comments</h3>

<c:forEach items="${comments}" var="comment">
	<c:if test="${fn:length(comments) != 0}">
		<c:if test="${comment.step == 0 && comment.motherNo == 0}">
				<span type='hidden' name='momno' value='${comment.motherNo}'></span>
				<input type='hidden' name='cno' value='${comment.no}'> <input
					type='hidden' name='arno' value='${comment.articleNo}'> <input
					type='hidden' name='step' value='${comment.step}'> 
					<a href='../user/${comment.writer.no}'>${comment.writer.nickname}</a>
					
					 <div class="mb-3 row">
        <div class="col-sm-12">
          <textarea class="form-control" id="exampleTextarea" rows="3" name='content' readonly>${comment.content}</textarea>
        </div>
      </div>
				<fmt:formatDate value="${comment.createdDate}" pattern="yyyy.MM.dd. HH:mm" />

<div class="mb-3 row">
        <div class="col-sm-5">
				<c:choose>
					<c:when test="${comment.state == 1}">
						<a class="btn btn-danger"
							href='../comment/inactivate?no=${comment.no}&articleNo=${comment.articleNo}'>미게시</a>
					</c:when>
					<c:otherwise>
						<a class="btn btn-outline-danger"
							href='../comment/activate?no=${comment.no}&articleNo=${comment.articleNo}'>게시</a>
					</c:otherwise>
				</c:choose>
  </div>
</div>

				${comment.state == 1 ? "게시중" :  "미게시중"}

			<c:forEach items="${comments}" var="childComment">
				<c:if
					test="${childComment.step == 1 && childComment.motherNo != 0 && childComment.motherNo == comment.no}">
					<p>
						<input type='hidden' name='momno' value='${childComment.motherNo}'>
						<input type='hidden' name='cno' value='${childComment.no}'>
						<input type='hidden' name='arno' value='${childComment.articleNo}'>
						<input type='hidden' name='step' value='${childComment.step}'>
						<span>&nbsp;&nbsp;┗━Re&nbsp;&nbsp;</span> 
						<a href='../user/${childComment.writer.no}'>${childComment.writer.nickname}</a>
							
        <div class="mb-3 row">
        <div class="col-sm-12">
          <textarea class="form-control" id="exampleTextarea" rows="3" name='content'>${childComment.content}</textarea>
        </div>
      </div>
							
						<fmt:formatDate value="${childComment.createdDate}"
							pattern="yyyy.MM.dd" />

      <div class="mb-3 row">
        <div class="col-sm-5">
						<c:choose>
							<c:when test="${comment.state == 1}">
								<a class="btn btn-danger"
									href='../comment/inactivate?no=${comment.no}&articleNo=${comment.articleNo}'>미게시</a>
							</c:when>
							<c:otherwise>
								<a class="btn btn-outline-danger"
									href='../comment/activate?no=${comment.no}&articleNo=${comment.articleNo}'>게시</a>
							</c:otherwise>
						</c:choose>
						  </div>
						</div>
						
						${childComment.state == 1 ? "게시중" :  "미게시중"}
				</c:if>
			</c:forEach>

			<hr color='gray'>

		</c:if>
	</c:if>
</c:forEach>
</div>
</div>