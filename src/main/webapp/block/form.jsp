<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>차단하기</title>
    <jsp:include page="/admin-header.jsp"></jsp:include>

</head>
<body>
  <a href='../admin/userList' style='text-decoration: none;'>전체회원관리</a>
  <a href='../report/list' style='text-decoration: none;'>신고내역</a>
  <a href='../block/list' style='text-decoration: none;'>활동정지회원</a>
	<h1>유저 차단하기</h1>
	<form action="add" method="post">
		차단일수 : <select name="blockedDates">
			<option value="1">1일</option>
			<option value="3">3일</option>
			<option value="7">7일</option>
			<option value="30">30일</option>
			<option value="9876">영구차단</option>
		</select><br> 차단사유 :
		<textarea name="blockedReason" cols="60" rows="10"></textarea>
		<br>
		<input type='hidden' name='reportNo' value='${report.no}'>
		<button class="btn btn-primary">차단하기</button>
	</form>
	 <jsp:include page="/footer.jsp"></jsp:include>
	 <script src="../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>