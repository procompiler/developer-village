<%@page import="com.devil.domain.Badge"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
