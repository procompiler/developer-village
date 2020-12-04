<%@page import="com.devil.domain.Article"%>
<%@page import="com.devil.domain.Comment"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>댓글 조회</title>
<link rel="stylesheet" type="text/css" href='../style.css'>
</head>

<body>
<hr size='3'>
<h2>Comments</h2>

<%SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");%>
<%Article article = (Article) request.getAttribute("article");%>
<%List<Comment> comments = (List<Comment>) request.getAttribute("comments");%>
<%if (comments != null) {
  for (Comment comment : comments) {
    if (comment.getStep() != 0) {
      continue;
    } else if (comment.getStep() == 0) {%>

<form action='../comment/update?no=<%=comment.getNo()%>&articleNo=<%=comment.getArticleNo()%>' method='post'>
	<input type='hidden' name='momno' value='<%=comment.getMotherNo()%>'>
	<input type='hidden' name='cno' value='<%=comment.getNo()%>'>
	<input type='hidden' name='arno' value='<%=article.getNo()%>'>
	<input type='hidden' name='step' value='<%=comment.getStep()%>'>
	<p>엄마</p>
	<img src='../upload/user/<%=comment.getWriter().getPhoto()%>.jpg_40x40.jpg' alt='[<%=comment.getWriter().getPhoto()%>].jpg]'>
	<a href='../user/detail?no=<%=comment.getWriter().getNo()%>'><%=comment.getWriter().getNickname()%></a>
	<textarea name='content' style="height:30px;width:400px;"><%=comment.getContent()%></textarea>
	<%=formatter.format(comment.getCreatedDate())%>
	<button>수정</button>
	<button type='button' class='btn-danger' onclick="location.href='../comment/delete?no=<%=comment.getNo()%>&articleNo=<%=article.getNo()%>'">삭제</button>
	<%=comment.getState() == 1 ? "정상 댓글" : "삭제된 댓글"%>
</form>
	
    <%}%>
    
<%for (Comment childComment : comments) {
  if (childComment.getStep() == 1 && childComment.getMotherNo() == comment.getNo()) {%>
		<form action='../comment/update?no=<%=childComment.getNo()%>&articleNo=<%=article.getNo()%>' method='post'>
			<input type='hidden' name='momno' value='<%=childComment.getMotherNo()%>'>
			<input type='hidden' name='cno' value='<%=childComment.getNo()%>'>
			<input type='hidden' name='arno' value='<%=article.getNo()%>'>
			<p>딸</p>
			<img src='../upload/user/<%=childComment.getWriter().getPhoto()%>.jpg_40x40.jpg' alt='[<%=childComment.getWriter().getPhoto()%>].jpg]'>
			<a href='../user/detail?no=<%=childComment.getWriter().getNo()%>'><%=childComment.getWriter().getNickname()%></a>
			<textarea name='content' style="height:30px;width:400px;"><%=childComment.getContent()%></textarea>
			<%=formatter.format(childComment.getCreatedDate())%>
			<button>수정</button>
			
			<button type='button' class='btn-danger' onclick="location.href='../comment/delete?no=<%=comment.getNo()%>&articleNo=<%=article.getNo()%>'">삭제</button>
			<%=comment.getState() == 1 ? "정상 댓글" : "삭제된 댓글"%>
    </form> 
  <%}%>
<%}%>


<form action='../comment/add' method='post'>
	<input type='hidden' name="momno" value='<%=comment.getNo()%>'><br>
	<input type='hidden' name="arno" value='<%=article.getNo()%>'><br>
	<input type='hidden' name="step" value='1'><br>
	<input type='text' name='content'><br>
	<button>대댓글</button>
</form>

<hr color='gray'>
<%}%>
<form method='post' action='../comment/add'>
	<input type='hidden' name="momno" value='0'><br>
	<input type='hidden' name="arno" value='<%=article.getNo()%>'><br>
	<input type='hidden' name="step" value='0'><br>
	<input type='text' name='content'><br>
	<button>댓글쓰기</button>
</form>
<%}%>
</body>
</html>
