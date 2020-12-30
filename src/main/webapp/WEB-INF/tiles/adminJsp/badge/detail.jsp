<%@page import="com.devil.domain.Badge"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
<h1>뱃지 정보</h1>

<div id="detail" class="row col-sm-8">
<h3>${badge.name}</h3><br>

<form action='updatePhoto' method='post' enctype='multipart/form-data'>
	<input type="hidden" name='no' value='${badge.no}'><br>
		<img src='../../upload/badge/${badge.photo}_80x80.png' alt='[뱃지 이미지]'  style='border-radius: 80px'>
	<div class="mb-4 mt-3">
	<div class="col-sm-5">
  <label for="photoFile" class="form-label">사진업로드</label>
  <input class="form-control-sm" name='photoFile' type="file">
	<br>
	<button class="btn btn-primary mt-3">이미지 변경</button>
  </div>
 </div>
</form>
<br>
<form action='update' method='post'>
	<input type='hidden' name='no' value='${badge.no}'>

        <label for="content" class="form-label col-sm-2 col-form-label">뱃지 설명</label>
		    <div class="mb-3 row">
        <div class="col-sm-12">
          <textarea class="form-control" rows="3" name='content'>${badge.content}</textarea>
        </div>
      </div>
      
	<button class="btn btn-primary">뱃지 수정</button>
</form>
<jsp:include page="../badgeStan/list.jsp"></jsp:include>

<a class='col-sm-3' href='list'>뱃지 목록으로</a>
</div>
</div>