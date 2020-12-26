<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>게시글 신고하기</h1>

<form action="reportArticle-send" method="get">
<br>
<h4>'${reportedArticle.writer.nickname}'님의 게시글을 신고합니다.</h4>
<br>
<input type='hidden' name='articleNo' value='${reportedArticle.no}'>
<h5>신고 사유를 선택하세요.</h5>
<select name="reason" class="form-select">
  <option value="1">욕설</option>
  <option value="2">권리침해</option>
  <option value="3">폭력적 또는 혐오성 게시글</option>
  <option value="4">불법광고</option>
  <option value="5">음란성</option>
  <option value="6">도배</option>
</select>
<button class="btn btn-primary" style="float:right;">신고하기</button>
</form>
