<%@page import="com.devil.domain.Badge"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뱃지 정보</title>
<!--<jsp:include page="/admin-header.jsp"></jsp:include> -->
<link rel="stylesheet" type="text/css" href='../badge-style.css'>
<link rel="stylesheet"
  href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
  integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
  crossorigin="anonymous" />
</head>
<body>
	<div class="badge">
 <div class="badgebox">

 	<div class="badgeinfotext">
  	뱃지 정보
 	</div>
 	<div>
 		<div class="firstBox" style="float:left">
		 	<div class="photo">
		 	<td><img style="width: 40px;" src='../../upload/badge/${badge.photo}_60x60.png'></td>
		 	</div>
		 	<div class="badgephotoupdatetext">
		 	사진 업로드
		 	</div>
 		</div>
 		<div class="secondBox">
		 	<div class="badgename">
		 	뱃지 이름
		 	</div>
		 	<div class="badgenamebox">
		 	뱃지이름박스
		 	</div>
 		</div>
 	</div>




 
 
	 <div class="badgeLine2">
	 	<span>뱃지 설명</span>
	 </div>
 	<div class="badgeexambox">
 	
 		<span id="badgeExam">
 		
 		</span>
	</div>
	
	
	
 </div>
 
 
 <div class="badge-update-box">
 	<div class="badge-update-box-text">
 	뱃지수정
 	</div>
 </div>
 </div>
 


  <script src="../../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>