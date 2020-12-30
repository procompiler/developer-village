<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h1 style="margin-left: 70px; font-weight: bold">게시글 작성</h1>
	<head>
  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css"
  />
  <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
  <style>
    .tui-editor-contents p, .tui-editor-contents h1, .tui-editor-contents h2, .tui-editor-contents h3, .tui-editor-contents h4, .tui-editor-contents h5, .tui-editor-contents h6{
      color: white;
    }
  </style>
</head>
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
		<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
		<input type="text" name="content" id="content" hidden='hidden'/>
		 <div id="editor">
		 </div>
		<script>
	      const editor = new toastui.Editor({
	          el: document.querySelector('#editor'),
	          previewStyle: 'vertical',
	          height: '500px'
	        });
		</script>
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
		<button type="submit" class="btn btn-primary" id="form-submit">게시글 작성</button>
		<button type='button' class='btn btn-danger'
			onclick="location.href='list'">취소</button>
	</form>
</div>
	<script>
	  
	  var formSubmitButton = document.querySelector("#form-submit");
	  formSubmitButton.addEventListener("click", function() {
	    var content = document.querySelector("#content");
	    content.value = editor.getHtml();
	  })
	  
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