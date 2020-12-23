<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>댓글 신고하기</h1>

<form action="reportComment-send" method="get">
<h2>신고사유</h2>
<input type='hidden' name='commentNo' value='${reportedComment.no}'>
<input type='hidden' name='commentArticleNo' value='${reportedComment.articleNo}'>
<select name="reason">
  <option value="1">욕설</option>
  <option value="2">권리침해</option>
  <option value="3">폭력적 또는 혐오성 댓글</option>
  <option value="4">불법광고</option>
  <option value="5">음란성</option>
  <option value="6">도배</option>
</select><br>
<button>댓글신고하기</button>
</form>