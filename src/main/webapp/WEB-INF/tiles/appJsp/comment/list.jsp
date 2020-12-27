<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style>
  .btn-right {
    float:right;
  }
</style>

<div style="margin-top:50px;">
	<h3>댓글</h3>
  
	<c:forEach items="${comments}" var="comment">
		<c:if test="${fn:length(comments) != 0}">
			<c:if test="${comment.step == 0 && comment.motherNo == 0}">
				<form action='../comment/update?no=${comment.no}&articleNo=${comment.articleNo}'
					    method='post' class="card-body">
					<input type='hidden' name='motherNo' value='${comment.motherNo}'>
					<input type='hidden' name='no' value='${comment.no}'> 
					<input type='hidden' name='articleNo' value='${comment.articleNo}'> 
					<input type='hidden' name='step' value='${comment.step}'> 
					
					<p>
					  <a href='../user/${comment.writer.no}'>
					  <img src='../../upload/user/${comment.writer.photo}_40x40.jpg'
                 style='border-radius: 70px' alt='[${comment.writer.photo}_40x40]'>
            <span style="font-weight:bold; font-size:105%;">${comment.writer.nickname}</span>
            </a>
            <c:if test="${comment.writer.no == article.writer.no}">[작성자]</c:if>
            <span class="card-subtitle mb-2 text-muted">
              <fmt:formatDate value="${comment.createdDate}" pattern="yyyy.MM.dd. HH:mm"/>
            </span>
            <c:if test="${comment.writer.no != loginUser.no}">
					  <a class="btn btn-danger" href='../report/reportComment?no=${comment.no}'>신고</a>
					  </c:if>
					  
					  <c:if test="${comment.writer.no == loginUser.no}">
           <button class="btn btn-primary btn-right">수정</button>
           <a class='btn btn-danger btn-right' href='../comment/delete?no=${comment.no}&articleNo=${comment.articleNo}'>삭제</a>
           </c:if>
					</p>
					
					<c:if test="${comment.state == 1}">
					  <textarea name='content' class="form-control" 
					            style="border-color:#4a4d53; background-color: #37393E"
					           <c:if test="${comment.writer.no != loginUser.no}">readonly</c:if>
					            >${comment.content}</textarea>
					</c:if>
					<c:if test="${comment.state == 0}">
					  <textarea name='' class="form-control" style="border-color:#4a4d53; background-color: #37393E" readonly>삭제된 댓글 입니다.</textarea>
					</c:if>
        <hr>
				</form>
        
				<c:forEach items="${comments}" var="childComment">
					<c:if test="${childComment.step == 1 && childComment.motherNo != 0 
					             && childComment.motherNo == comment.no}">
						<form action='../comment/update?no=${childComment.no}&articleNo=${childComment.articleNo}'
							    method='post' class="card-body" style="margin-left:80px;">
							<input type='hidden' name='mothernNo' value='${childComment.motherNo}'> 
							<input type='hidden' name='no' value='${childComment.no}'> 
							<input type='hidden' name='artcleNo' value='${childComment.articleNo}'>
							<input type='hidden' name='step' value='${childComment.step}'>
							
							<p >
                <a href='../user/${childComment.writer.no}'>
                <img src='../../upload/user/${childComment.writer.photo}_40x40.jpg'
                     style='border-radius: 70px' alt='[${childComment.writer.photo}_40x40]'>
                <span style="font-weight:bold; font-size:110%;">${childComment.writer.nickname}</span>
                </a>
                <c:if test="${childComment.writer.no == article.writer.no}">[작성자]</c:if>
                <span class="card-subtitle mb-2 text-muted">
							   <fmt:formatDate value="${childComment.createdDate}" pattern="yyyy.MM.dd. HH:mm" />
							  </span>
							  
							  <c:if test="${childComment.writer.no != loginUser.no}">
							  <a class="btn btn-danger" href='../report/reportComment?no=${childComment.no}'>신고</a>
							  </c:if>
							  
							 <c:if test="${childComment.writer.no == loginUser.no}">
              <button class="btn btn-primary btn-right">수정</button>
              <a class='btn btn-danger btn-right' href='../comment/delete?no=${childComment.no}&articleNo=${childComment.articleNo}'>삭제</a>
              </c:if>
              
							<c:if test="${childComment.state == 1}">
							  <textarea name='content' class="form-control" 
							            style="border-color:#4a4d53; background-color: #37393E"
							            <c:if test="${childComment.writer.no != loginUser.no}">readonly</c:if>
							            >${childComment.content}</textarea>
							</c:if>
							<c:if test="${childComment.state == 0}">
							  <textarea name='' class="form-control" style="border-color:#4a4d53; background-color: #37393E" readonly>삭제된 댓글 입니다.</textarea>
              </c:if>
              </p>
						 <hr>
						</form>
					</c:if>
				</c:forEach>
				
				<form method='post' action='../comment/add' style="margin-left:80px;">
          <input type='hidden' name="motherNo" value='${comment.no}'>
          <input type='hidden' name="articleNo" value='${comment.articleNo}'>
          <input type='hidden' name="step" value='1'><br>
          
        <p><a href='../user/${loginUser.no}'>
          <img src='../../upload/user/${loginUser.photo}_40x40.jpg'
               style='border-radius: 70px' alt='[${loginUser.photo}_40x40]'>
          <span style="font-weight:bold; font-size:105%;">${loginUser.nickname}</span></a></p>
          <textarea class="form-control" name='content' placeholder='답글을 남겨보세요!'></textarea>
          <button class="btn btn-primary" style="float:right;">등록</button>
        </form>
        <br>
      </c:if>
    </c:if>
  </c:forEach>
    
	<form method='post' action='../comment/add'>
      <input type='hidden' name="motherNo" value='0'>
      <input type='hidden' name="articleNo" value='${article.no}'>
      <input type='hidden' name="step" value='0'><br>
      
      <p><a href='../user/${loginUser.no}'>
        <img src='../../upload/user/${loginUser.photo}_40x40.jpg'
              style='border-radius: 70px' alt='[${loginUser.photo}_40x40]'>
        <span style="font-weight:bold; font-size:105%;">${loginUser.nickname}</span></a></p>
      <textarea class="form-control" name='content' placeholder='댓글을 남겨보세요!'></textarea>
      <button class="btn btn-primary" style="float:right;">등록</button><br>
  </form>
</div>

