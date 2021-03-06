<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>뱃지 기준</h2>
  <c:forEach items="${badgeStans}" var="s">
	<form action='../badgeStan/update' method='post'>
		<input type='hidden' name='badgeNo' value='${badge.no}' /> <input
			type='hidden' name='no' value='${s.no}' /> <input type='hidden'
			name='evaluationNo' value='${s.evaluationNo}' /> <input type='text'
			name='evaluationName' readonly value='${s.evaluationName}' /> <input
			type='number' name='count' value='${s.count}' />
		<button class="btn btn-primary">수정</button>
		<a class="btn btn-outline-danger"
			href="../badgeStan/delete?no=${s.no}&badgeNo=${s.badgeNo}">삭제</a> <br>
	</form>
  </c:forEach>

<form action='../badgeStan/add' method='post'>
	<input type='hidden' name="badgeNo" value="${badge.no}" /> <select
		name="evaluationNo" class="form-select">
		<option selected>수여기준</option>
		<option value="1">가입일수</option>
		<option value="2">팔로워수</option>
		<option value="3">게시글수</option>
		<option value="4">댓글수</option>
	</select> <input type="number" name="count" value="0" />
	<button class="btn btn-primary">기준 추가</button>
</form>
