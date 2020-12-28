<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h1 style="margin-left: 70px; font-weight: bold">게시글 작성</h1>
	
<div id="articleForm">
	<form id="articleForm" action='add' method='post'>
		카테고리 <select class="form-select" aria-label="Default select example" name='categoryNo'>
    	<option value='1'>자유게시판</option> 
			<option value='2'>QnA</option>
			<option value='3'>채용공고</option>
			<option value='4'>스터디</option>
		</select>
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label"></label>
			<input  type="text" class="form-control"
				     id="inputTitle" placeholder="게시글 제목" name='title'>
		</div>
		<div class="mb-3">
			<label for="exampleFormControlTextarea1" class="form-label"
				></label>
			<textarea class="form-control" id="inputContent" placeholder="게시글을 작성하세요!"
				rows="20" name='content'></textarea>
		</div>
		<p>
			태그<br>
			<c:forEach items="${tags}" var="tag">
					<input class="form-check-input" type="checkbox" value="${tag.no}"
						id="flexCheckDefault" name="tagNo"> <label
						class="form-check-label" for="flexCheckDefault">
						${tag.name}</label>
				<c:if test="${tag.no % 9 == 0 }"><br></c:if>
			</c:forEach>
		</p>

		<button type="submit" class="btn btn-primary">게시글 작성</button>
		<button type='button' class='btn btn-danger'
			onclick="location.href='list'">취소</button>
	</form>
	<script>
	  

	  
	document.querySelector("#articleForm").onsubmit = () => {
		var query = 'input[name="tagNo"]:checked';
		var selectedElements =  document.querySelectorAll(query);
		var selectedElementsCnt = selectedElements.length;
		
		if (document.querySelector("#inputTitle").value.length < 1 ||
			document.querySelector("#inputContent").value.length < 1 ||
			selectedElementsCnt < 1 ) {
			alert("필수 입력 항목을 모두 채우세요!")
		  return false;
		}
	};
	</script>
</div>