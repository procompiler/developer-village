<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
  
<h1>뱃지 등록</h1>
<form id="badgeForm" action="add" method="post" enctype="multipart/form-data">
이름 * : <input id="inputName" type="text" name="name"><br>
내용  : <textarea name="content" cols="60" rows="10"></textarea><br>
뱃지 사진*  : <input id="inputPhoto" type="file" name="photoFile"><br>
<button class="btn btn-primary">등록</button>
</form>
<script>
document.querySelector("#badgeForm").onsubmit = () => {
	if (document.querySelector("#inputName").value.length < 4 ||
			document.querySelector("#inputPhoto").value.length < 1) {
		alert("필수 입력 항목을 모두 채우세요!")
	  return false;
	}
};
</script>