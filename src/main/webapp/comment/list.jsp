<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>댓글 목록</title>
</head>
<body>
	<h2>Comments</h2>

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
					<img src='../upload/user/${comment.writer.photo}_40x40.jpg'
						   style='border-radius: 70px' alt='[${comment.writer.photo}_40x40]'>
					<a href='../user/detail?no=${comment.writer.no}'>${comment.writer.nickname}</a>
					<textarea name='content' style="height: 30px; width: 400px;">${comment.content}</textarea>
					<fmt:formatDate value="${comment.createdDate}" pattern="yyyy.MM.dd" />
					<button class="btn btn-primary">수정</button>
					<a class='btn btn-danger' href='../comment/delete?no=${comment.no}&articleNo=${comment.articleNo}'>삭제</a>
					<a class="btn btn-danger"
                  href='../report/reportComment?no=${comment.no}'>신고</a>
					
					${comment.state == 1 ? "정상 댓글" :  "삭제된 댓글"}
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
							<img src='../upload/user/${childComment.writer.photo}_40x40.jpg'
								   style='border-radius: 70px'
								   alt='[${childComment.writer.photo}_40x40]'>
							<a href='../user/detail?no=${childComment.writer.no}'>${childComment.writer.nickname}</a>
							<textarea name='content' style="height: 30px; width: 400px;">${childComment.content}</textarea>
							<fmt:formatDate value="${childComment.createdDate}" pattern="yyyy.MM.dd" />
							<button class="btn btn-primary">수정</button>
							<a class='btn btn-danger' href='../comment/delete?no=${childComment.no}&articleNo=${childComment.articleNo}'>삭제</a>
          <a class="btn btn-danger"
                  href='../report/reportComment?no=${childComment.no}'>신고</a>
							
							${childComment.state == 1 ? "정상 댓글" :  "삭제된 댓글"}
						</form>
					</c:if>
				</c:forEach>
				
				<form action='../comment/add' method='post'>
					<input type='hidden' name="momno" value='${comment.no}'><br>
					<input type='hidden' name="arno" value='${comment.articleNo}'><br>
					<input type='hidden' name="step" value='1'><br> <input
						type='text' name='content'><br>
					<button class='btn btn-primary'>대댓글</button>
				</form>
				<hr color='gray'>

			</c:if>
		</c:if>
	</c:forEach>

	<form method='post' action='../comment/add'>
		<input type='hidden' name="momno" value='0'><br> <input
			type='hidden' name="arno" value='${param.no}'><br> <input
			type='hidden' name="step" value='0'><br> <input
			type='text' name='content'><br>
		<button class='btn btn-primary'>댓글쓰기</button>
	</form>
	<script src="../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
