<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../user/info.jsp"></jsp:include>
<h2>알림</h2>
<div class="d-grid gap-3" />
<c:forEach items="${notificationList}" var="n">
		<div class="card">
			<div class="row g-0">
			<c:choose>
			  <c:when test="${n.type == 3}">
				<div class="col-sm-2">
					<div class="text-center mr-2 mt-4">
						<img class="card-img-top rounded-circle" alt='[${n.follower.photo}]'
							src='../../upload/user/${n.follower.photo}_60x60.jpg' />
					</div>
				</div>
				<div class="col-sm-10">
					<div class="card-body">
						<p class="card-text"><a class="noti-title" data-no="${n.no}" href="../user/${n.follower.no}">${n.follower.nickname}님이 팔로우하셨습니다.</a></p>
						<p class="card-text">
							<small class="text-muted">${n.difTime}</small>
						</p>
					</div>
				</div>
				</c:when>
				<c:when test="${n.type == 1 || n.type == 2}">
        <div class="col-sm-2">
          <div class="text-center mr-2 mt-4">
            <img class="card-img-top rounded-circle" alt='[${n.comment.writer.nickname}]'
              src='../../upload/user/${n.comment.writer.photo}_60x60.jpg' />
          </div>
        </div>
        <div class="col-sm-10">
          <div class="card-body">
            <p class="card-text">
              <a class="noti-title" data-no="${n.no}" href="../article/${n.comment.articleNo}">
              <c:if test="${n.type == 1}">
              ${n.comment.writer.nickname}님이 <b>"${n.comment.articleTitle}"</b> 게시글에 댓글을 남겼습니다.
              </c:if>
              <c:if test="${n.type == 2}">
              ${n.comment.writer.nickname}님이<b>"${n.comment.articleTitle}"</b> 게시글의 내 댓글에 대댓글을 남겼습니다.
              </c:if>
              </a>
              </p>
            <p class="card-text">
              <small class="text-muted">${n.difTime}</small>
            </p>
          </div>
        </div>
        </c:when>
				<c:when test="${n.type == 4}">
        <div class="col-sm-2">
          <div class="text-center mr-2 mt-4">
            <img class="card-img-top rounded-circle" alt='[${n.badge.photo}]'
              src='../../upload/badge/${n.badge.photo}_60x60.png' />
          </div>
        </div>
        <div class="col-sm-10">
          <div class="card-body">
            <p class="card-text">
              <a href="../collect/list" class="noti-title" data-no="${n.no}">
              <b>${n.badge.name}</b> 뱃지가 도착했습니다!
              </a>
              </p>
            <p class="card-text">
              <small class="text-muted">${n.difTime}</small>
            </p>
          </div>
        </div>
        </c:when>
			</c:choose>
			</div>
		</div>
</c:forEach>
</div>
</div>
</div>
<div class="col-3"></div>
</div>

<script>
var el = document.querySelectorAll(".noti-title");
var notiNo;

for (var e of el) {
  e.onclick = function(e) {
    notiNo = e.target.getAttribute("data-no");
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "../ajax/notification/updateReadDate?no=" + notiNo);
    xhr.send();
  };
}

</script>
