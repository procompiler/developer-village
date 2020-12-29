<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

  <a href='../admin/user/list' style='text-decoration: none;'>전체회원관리</a>
  <a href='../report/list' style='text-decoration: none;'>신고내역</a>
  <a href='../block/list' style='text-decoration: none;'>활동정지회원</a>
 
<div class="row">
  <div class="row cols-sm-6 mt-4">
	<h1>유저 차단하기</h1>
	<form action="add" method="post">
		차단일수 : <select name="blockedDates">
			<option value="1">1일</option>
			<option value="3">3일</option>
			<option value="7">7일</option>
			<option value="30">30일</option>
			<option value="9876">영구차단</option>
		</select>
		<br>
		<div class="form-floating">
     <textarea class="form-control" placeholder="차단 사유를 적어주세요.."
      id="floatingTextarea2" name="blockedReason" style="height: 200px; width: 400px"></textarea>
    <label for="floatingTextarea2">차단 사유</label>
   </div>

		<br>
		<input type='hidden' name='reportNo' value='${report.no}'>
		<input type='hidden' name='reportLinkNo' value='${param.reportLinkNo}'>
		<button class="btn btn-primary">차단하기</button>
	</form>
	</div>
</div>