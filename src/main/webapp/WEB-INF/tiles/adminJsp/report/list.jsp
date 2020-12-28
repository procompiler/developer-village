<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

  <div class="mini-navi">
  <a href='../user/list' style='text-decoration: none;'>전체 회원관리</a>
  <a href='../report/list' style='text-decoration: none;'>신고내역</a>
  <a href='../block/list' style='text-decoration: none;'>활동정지회원</a>
  </div>
  <h1>
    <a href='../user/list' style='text-decoration: none;'>신고내역</a>
  </h1>

	<table border='1'>
	<thead>
		<tr>
			<th>번호</th>
			<th>신고자</th>
			<th>피신고자</th>
			<th>신고날짜</th>
			<th>신고링크</th>
			<th>신고사유</th>
			<th>신고승인</th>
		</tr>
		</thead>
	<tbody>
		<c:forEach items="${reportList}" var="report">
			<tr>
				<td>${report.no}</td>
				<td id="profile">${report.reporter.nickname}[${report.reporter.email}]</td>
				<td id="profile"><c:if test="${report.reportedArticle == null}">
          ${report.reportedComment.writer.nickname}[${report.reportedComment.writer.email}]
        </c:if> <c:if test="${report.reportedArticle != null}">
          ${report.reportedArticle.writer.nickname}[${report.reportedArticle.writer.email}]
        </c:if></td>
				<td>${report.getCreatedDate()}</td>
				<td><c:if test="${report.reportedArticle == null}">
						<a href='../article/${report.reportedComment.articleNo}'
							style='text-decoration: none;'>댓글</a>
					</c:if> <c:if test="${report.reportedArticle != null}">
						<a href='../article/${report.reportedArticle.no}'
							style='text-decoration: none;'>게시글</a>
					</c:if></td>
				<td><c:choose>
						<c:when test="${report.reportTypeNo == 1}">
							<p>욕설</p>
						</c:when>
						<c:when test="${report.reportTypeNo == 2}">
							<p>권리침해</p>
						</c:when>
						<c:when test="${report.reportTypeNo == 3}">
							<p>폭력적 또는 혐오성 게시글</p>
						</c:when>
						<c:when test="${report.reportTypeNo == 4}">
							<p>불법광고</p>
						</c:when>
						<c:when test="${report.reportTypeNo == 5}">
							<p>음란성</p>
						</c:when>
						<c:otherwise>
							<p>도배</p>
						</c:otherwise>
					</c:choose></td>

				<td>
					<form action="../block/form" method=get>
						<input type='hidden' name='reportNo' value='${report.no}'>
						<c:choose>
							<c:when test="${report.status == 1}">
								<button class="btn btn-primary">신고승인</button>
							</c:when>
							<c:when test="${report.status == 2}">
								<p style="color:red">차단중</p>
							</c:when>
							<c:otherwise>
								<p style="color:yellow;">차단해제</p>
							</c:otherwise>
						</c:choose>
					</form>
				</td>

		</c:forEach>
	</tbody>
	</table>