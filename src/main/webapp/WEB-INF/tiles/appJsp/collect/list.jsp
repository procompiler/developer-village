<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
#sortable {
	list-style-type: none;
	margin: 0;
	padding: 0;
}

#sortable li {
	margin: 3px 3px 3px 0;
	padding: 20px;
	float: left;
	text-align: center;
}

#sortable img {
	width: 140px;
}
</style>


<jsp:include page="../user/info.jsp"></jsp:include>
<h2>뱃지리스트</h2>
<ul id="sortable">
	<c:forEach items="${badgeList}" var="b">
		<li data-no="${b.no}"><img src="../../upload/badge/${b.photo}_160X160.png" /></li>
	</c:forEach>
</ul>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#sortable").sortable({
			start: function(e, ui) {
				$(this).attr('data-previndex', ui.item.index());
			},
			update : function(e, ui) {
				var arr = [];
				$("#sortable li").each(function (index, element) {
					console.log($(element).attr("data-no"));
					arr.push($(element).attr("data-no"));
				})
				console.log(arr);
			    $.ajax({
			        type: "POST",
			        url: "../ajax/collect/updateOrder",   // 서버단 메소드 url 
			        data : {
			        	"order": arr.toString()
			        },
			        dataType  : "text",
			        success  : function(data) {
			          // 정상적으로 response 시 
			        }
			      });
				/* 
				var newOrder = Number(ui.item.index()) + 1; // index값이 0에서 시작하기 때문에 1 더하기 
				var oldOrder = Number($(this).attr('data-previndex')) + 1;
				var badgeNo = ui.item.attr("data-no"); // item group key
				
				
				*/
			}
		});
		$("#sortable").disableSelection();
	});
	/*
	function getOrders() {
		  var listElements = $("#sortable").children();
		  listElements.forEach(function(element)) {
			  
		  }
		  console.log(listElements);
		  return listElements;
	}
	*/
</script>
