<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style>
.badge-list {
	list-style-type: none;
	margin: 0;
	padding: 0;
}

.badge-list li {
	margin: 3px 3px 3px 0;
	padding: 15px;
	float: left;
	text-align: center;
}

.badge-list img {
	width: 120px;
}

#sortable img {
  cursor: pointer;
}

</style>

<div class="col-3">
  <c:choose>
  <c:when test="${user.no != loginUser.no && null ne user}">
	<ul class="badge-list">
  </c:when>
  <c:otherwise>
   <ul class="badge-list" id="sortable">
  </c:otherwise>
  </c:choose>
		<c:forEach items="${badgeList}" var="b">
			<li data-no="${b.no}"><img
				src="../../upload/badge/${b.photo}_160X160.png" /></li>
		</c:forEach>
	</ul>

	<script>
		$(function() {
			$("#sortable").sortable({
				start : function(e, ui) {
					$(this).attr('data-previndex', ui.item.index());
				},
				update : function(e, ui) {
					var arr = [];
					$("#sortable li").each(function(index, element) {
						console.log($(element).attr("data-no"));
						arr.push($(element).attr("data-no"));
					})
					console.log(arr);
					$.ajax({
						type : "POST",
						url : "../ajax/collect/updateOrder", // 서버단 메소드 url 
						data : {
							"order" : arr.toString()
						},
						dataType : "text",
						success : function(data) {
						}
					});
				}
			});
			$("#sortable").disableSelection();
		});
	</script>
	</div>
</div>