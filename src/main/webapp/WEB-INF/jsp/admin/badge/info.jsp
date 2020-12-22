<%@page import="com.devil.domain.Badge"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뱃지 정보</title>
<!--<jsp:include page="/admin-header.jsp"></jsp:include> -->
<link rel="stylesheet" type="text/css" href='../../badge-style.css'>
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
 	<table>
 		<tr>
 			<td>
			 	<div class="photo">
			 	<img style="width: 40px;" src='../../upload/badge/${badge.photo}_60x60.png'>
			 	</div>
			 	<div class="badgephotoupdatetext">
			 	사진 업로드
			 	</div>
 			</td>
 			<td>
		 		<div class="badgeinnerboxrighttop">
				 	<div class="badgename">
				 	뱃지 이름
				 	</div>
				 	<div class="badgenamebox">
				 	뱃지이름박스
				 	</div>
				 	<div class="badgenameboxtext">sdf</div>
		 		</div>
 			</td>
 		</tr>
 		<tr>
 			<td colspan="2">
				 <div class="badgeinnerboxbottom">
				 	<span>뱃지 설명</span>
				 </div>
			 	<div class="badgeexambox">
			 		<span id="badgeExam">
			 		 sdfsdffds
			 		</span>
				</div>
 			</td>
 		</tr> 	
 	</table>
	
	
	
 </div>
 
 
 <div class="badge-update-box">
 	<div id='textnameupdate' class="badge-update-box-text" >
	 	<span style="
	 	position: relative;
	 	top: 25%;
	 	">
	 		뱃지수정
	 	</span>
 	</div>
 </div>

 </div>
 


  <script src="../../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>