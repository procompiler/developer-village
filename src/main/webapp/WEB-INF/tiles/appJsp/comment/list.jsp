<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

  <br><br>
	<h3>댓글</h3>
  
	<c:forEach items="${comments}" var="comment">
		<c:if test="${fn:length(comments) != 0}">
			<c:if test="${comment.step == 0 && comment.motherNo == 0}">
				<form action='../comment/update?no=${comment.no}&articleNo=${comment.articleNo}'
					    method='post' class="card-body">
					<input type='hidden' name='momno' value='${comment.motherNo}'>
					<input type='hidden' name='cno' value='${comment.no}'> 
					<input type='hidden' name='arno' value='${comment.articleNo}'> 
					<input type='hidden' name='step' value='${comment.step}'> 
					
					<p>
					  <a href='../user/${comment.writer.no}'>
					  <img src='../../upload/user/${comment.writer.photo}_40x40.jpg'
                 style='border-radius: 70px' alt='[${comment.writer.photo}_40x40]'>
            <span style="font-weight:bold; font-size:105%;">${comment.writer.nickname}</span>
            </a>
            <span class="card-subtitle mb-2 text-muted">
              <fmt:formatDate value="${comment.createdDate}" pattern="yyyy.MM.dd. HH:mm"/>
            </span>
					  
					</p>
					
					<c:if test="${comment.state == 1}">
					  <textarea name='content' class="form-control" 
					            style="border-color:#4a4d53; background-color: #37393E">${comment.content}</textarea>
					</c:if>
					<c:if test="${comment.state == 0}">
					  <textarea name='' class="form-control" style="border-color:#4a4d53; background-color: #37393E" readonly>삭제된 댓글 입니다.</textarea>
					</c:if>
					
					<button class="btn btn-primary">수정</button>
					<a class='btn btn-danger' href='../comment/delete?no=${comment.no}&articleNo=${comment.articleNo}'>삭제</a>
					<a class="btn btn-danger"
             href='../report/reportComment?no=${comment.no}'>신고</a>
        <hr>
				</form>
        
				<c:forEach items="${comments}" var="childComment">
					<c:if test="${childComment.step == 1 && childComment.motherNo != 0 
					             && childComment.motherNo == comment.no}">
						<form action='../comment/update?no=${childComment.no}&articleNo=${childComment.articleNo}'
							    method='post' class="card-body" style="margin-left:80px;">
							<input type='hidden' name='momno' value='${childComment.motherNo}'> 
							<input type='hidden' name='cno' value='${childComment.no}'> 
							<input type='hidden' name='arno' value='${childComment.articleNo}'>
							<input type='hidden' name='step' value='${childComment.step}'>
							
							<p>
                <a href='../user/${childComment.writer.no}'>
                <img src='../../upload/user/${childComment.writer.photo}_40x40.jpg'
                     style='border-radius: 70px' alt='[${childComment.writer.photo}_40x40]'>
                <span style="font-weight:bold; font-size:110%;">${childComment.writer.nickname}</span>
                </a>
                <span class="card-subtitle mb-2 text-muted">
							   <fmt:formatDate value="${childComment.createdDate}" pattern="yyyy.MM.dd. HH:mm" />
							  </span>
              </p>
							
							<c:if test="${childComment.state == 1}">
							  <textarea name='content' class="form-control" style="border-color:#4a4d53; background-color: #37393E">${childComment.content}</textarea>
							</c:if>
							<c:if test="${childComment.state == 0}">
							  <textarea name='' class="form-control" style="border-color:#4a4d53; background-color: #37393E" readonly>삭제된 댓글 입니다.</textarea>
              </c:if>
							<button class="btn btn-primary">수정</button>
							<a class='btn btn-danger' href='../comment/delete?no=${childComment.no}&articleNo=${childComment.articleNo}'>삭제</a>
              <a class="btn btn-danger" href='../report/reportComment?no=${childComment.no}'>신고</a>
						 <hr>
						</form>
					</c:if>
				</c:forEach>
				
				<form method='post' action='../comment/add'>
          <input type='hidden' name="momno" value='${comment.no}'>
          <input type='hidden' name="arno" value='${comment.articleNo}'>
          <input type='hidden' name="step" value='1'><br>
          
        <p><a href='../user/${loginUser.no}'>
          <img src='../../upload/user/${loginUser.photo}_40x40.jpg'
               style='border-radius: 70px' alt='[${loginUser.photo}_40x40]'>
          <span style="font-weight:bold; font-size:105%;">${loginUser.nickname}</span>
        </a></p>

          <textarea class="form-control" name='content' placeholder='답글을 남겨보세요!'></textarea>
          <button class="btn btn-primary" style="margin-left:1200px;">등록</button>
        </form>
				
				<hr color='gray'>

			</c:if>
		</c:if>
	</c:forEach>

	<form method='post' action='../comment/add'>
      <input type='hidden' name="momno" value='0'>
      <input type='hidden' name="arno" value='${param.no}'>
      <input type='hidden' name="step" value='0'><br>
      <p>
        <a href='../user/${loginUser.no}'>
        <img src='../../upload/user/${loginUser.photo}_40x40.jpg'
              style='border-radius: 70px' alt='[${loginUser.photo}_40x40]'>
        <span style="font-weight:bold; font-size:105%;">${loginUser.nickname}</span>
      </p>
      <textarea class="form-control" name='content' placeholder='댓글을 남겨보세요!'></textarea>
    <button class="btn btn-primary" style="margin-left:1200px;">등록</button>
  </form>


	<script src="../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
