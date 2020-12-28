<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<h1>태그 수정</h1>
	<br><h3>${tag.name}</h3><br>

	<form action='updatePhoto' method='post' enctype='multipart/form-data'>
		<input type='hidden' name='no' value='${tag.no}'>
		 <img
			src='../../upload/tag/${tag.photo}_160x160.png' alt='[태그 이미지]' style='border-radius: 80px'>
		<input type='file' name='photoFile'>
		<button class="btn btn-primary">이미지 변경</button>
	</form>

<br><br>
	<form action='update' method='post'>
		<input type='hidden' name='no' value='${tag.no}'>
		<input type='hidden' name='name' value='${tag.name}'>
		<p>
			태그색: <input type='color' name='tagColor' value='${tag.tagColor}'>
			폰트색: <input type='color' name='fontColor' value='${tag.fontColor}'>
		</p><br>
		<button class="btn btn-primary">태그 수정</button>
		<a href='delete?no=${tag.no}' class="btn btn-danger"
			style='color: white;'>태그삭제</a>
		<a href='list' class="btn btn-primary" style='color: white;'>목록으로</a>
	</form>
