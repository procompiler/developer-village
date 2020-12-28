<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="row">
	<h1>태그 수정</h1>
	
	<div id="detail" class="row col-sm-8">
	<h3>${tag.name}</h3><br>

	<form action='updatePhoto' method='post' enctype='multipart/form-data'>
		<input type='hidden' name='no' value='${tag.no}'><br>
		 <img src='../../upload/tag/${tag.photo}_160x160.png' alt='[태그 이미지]' style='border-radius: 80px'>
  <div class="mb-4 mt-3">
   <div class="col-sm-5">
    <label for="photoFile" class="form-label">사진업로드</label>
     <input class="form-control form-control-sm bg-dark" name='photoFile' type="file">
		<button class="btn btn-primary mt-3">이미지 변경</button>
		</div>
	 </div>
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
	</form>
<a class='col-sm-3' href='list'>태그 목록으로</a>
</div>
</div>