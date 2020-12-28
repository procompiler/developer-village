<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<h1>태그 추가</h1>
<form id=tagForm action="../tag/add" method="post" enctype="multipart/form-data">
태그 이름* : <input id="inputName" type="text" name="name"><br>
태그 색상 : <input id="inputTagColor" type="color" name="tagColor"><br>
폰트 색상 : <input id="inputFontColor" type="color" name="fontColor"><br>
태그 사진 : <input type="file" name="photoFile"><br>
<button class="btn btn-primary">등록</button>
</form>
<script>
document.querySelector("#tagForm").onsubmit = () => {
	if (document.querySelector("#inputName").value.length < 1 ||
		alert("필수 입력 항목을 모두 채우세요!")
	  return false;
	}
};
</script>