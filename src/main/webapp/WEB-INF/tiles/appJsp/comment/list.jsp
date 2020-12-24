<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<p><h2>Comments</h2></p>
  
	<c:forEach items="${comments}" var="comment">
		<c:if test="${fn:length(comments) != 0}">
			<c:if test="${comment.step == 0 && comment.motherNo == 0}">
				<form
					action='../comment/update?no=${comment.no}&articleNo=${comment.articleNo}'
					method='post'>
					<input type='hidden' name='momno' value='${comment.motherNo}'>
					<input type='hidden' name='cno' value='${comment.no}'> 
					<input type='hidden' name='arno' value='${comment.articleNo}'> 
					<input type='hidden' name='step' value='${comment.step}'> 
					
					<p>
					<img src='../../upload/user/${comment.writer.photo}_40x40.jpg'
						   style='border-radius: 70px' alt='[${comment.writer.photo}_40x40]'>
					<a href='../user/detail?no=${comment.writer.no}'>${comment.writer.nickname}</a>
					</p>
					
					<c:if test="${comment.state == 1}">
					  <textarea name='content' class="form-control" style="border-color:#4a4d53; background-color: #37393E">${comment.content}</textarea>
					</c:if>
					<c:if test="${comment.state == 0}"><textarea name='' style="height: 30px; width: 400px;" readonly>삭제된 댓글 입니다.</textarea></c:if>
					
					<fmt:formatDate value="${comment.createdDate}" pattern="yyyy.MM.dd" />
					<button class="btn btn-primary">수정</button>
					<a class='btn btn-danger' href='../comment/delete?no=${comment.no}&articleNo=${comment.articleNo}'>삭제</a>
					<a class="btn btn-danger"
                  href='../report/reportComment?no=${comment.no}'>신고</a>
				</form>

				<c:forEach items="${comments}" var="childComment">
					<c:if
						test="${childComment.step == 1 && childComment.motherNo != 0 && childComment.motherNo == comment.no}">
						<form
							action='../comment/update?no=${childComment.no}&articleNo=${childComment.articleNo}'
							method='post'>
							<input type='hidden' name='momno' value='${childComment.motherNo}'> 
							<input type='hidden' name='cno' value='${childComment.no}'> 
							<input type='hidden' name='arno' value='${childComment.articleNo}'>
							<input type='hidden' name='step' value='${childComment.step}'>
							<span>&nbsp;&nbsp;┗━Re&nbsp;&nbsp;</span> 
							
							<img src='../../upload/user/${childComment.writer.photo}_40x40.jpg'
								   style='border-radius: 70px'
								   alt='[${childComment.writer.photo}_40x40]'>
							<a href='../user/detail?no=${childComment.writer.no}'>${childComment.writer.nickname}</a>
							<c:if test="${childComment.state == 1}">
							  <textarea name='content' style="height: 30px; width: 400px;">${childComment.content}</textarea>
							 </c:if>
              <c:if test="${childComment.state == 0}">삭제된 댓글 입니다.</c:if>
							<fmt:formatDate value="${childComment.createdDate}" pattern="yyyy.MM.dd" />
							<button class="btn btn-primary">수정</button>
							<a class='btn btn-danger' href='../comment/delete?no=${childComment.no}&articleNo=${childComment.articleNo}'>삭제</a>
          <a class="btn btn-danger"
                  href='../report/reportComment?no=${childComment.no}'>신고</a>
							
						</form>
					</c:if>
				</c:forEach>
				
				<form method='post' action='../comment/add'>
          <div class="input-group">
          <input type='hidden' name="momno" value='${comment.no}'>
          <input type='hidden' name="arno" value='${comment.articleNo}'>
          <input type='hidden' name="step" value='1'><br>
          <span class="input-group-text" style="border-color:#4a4d53; background-color: #37393E">
          <img src='../../upload/user/${loginUser.photo}_40x40.jpg'
               style='border-radius: 70px' alt='[${loginUser.photo}_40x40]'>
          ${loginUser.nickname}</span>
          <textarea class="form-control" aria-label="With textarea" name='content' placeholder='답글을 남겨보세요'></textarea>
          </div>
          <button class="btn btn-primary" style="margin-left:1200px;">등록</button>
        </form>
				
				<hr color='gray'>

			</c:if>
		</c:if>
	</c:forEach>

	<form method='post' action='../comment/add'>
    <div class="input-group">
      <input type='hidden' name="momno" value='0'>
      <input type='hidden' name="arno" value='${param.no}'>
      <input type='hidden' name="step" value='0'><br>
      <span class="input-group-text" style="border-color:#4a4d53; background-color: #37393E">
        <img src='../../upload/user/${loginUser.photo}_40x40.jpg'
             style='border-radius: 70px' alt='[${loginUser.photo}_40x40]'>
        ${loginUser.nickname}</span>
      <textarea class="form-control" aria-label="With textarea" name='content' placeholder='댓글을 남겨보세요'></textarea>
    </div>
<<<<<<< HEAD
	</form>
	
	<form method='post' action='../comment/add'>
  <div class="input-group">
  <span class="input-group-text" style="background-color: #37393E">  <img src='../../upload/user/${loginUser.photo}_40x40.jpg'
               style='border-radius: 70px' alt='[${loginUser.photo}_40x40]'> ${loginUser.nickname}</span>
  <textarea class="form-control" aria-label="With textarea" name='content' placeholder='댓글을 남겨보세요' cols="20px"></textarea>
  </div>
  <button class="btn btn-primary" style="margin-left:1200px;">등록</button>
</form>
=======
    <button class="btn btn-primary" style="margin-left:1200px;">등록</button>
  </form>


	<script src="../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
>>>>>>> d05e7d3268bd22269b2c964fb870b8dfa41375e8
