<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<h1>
		<c:choose>
			<c:when test="${article.categoryNo == 1}">
				<p>커뮤니티</p>
			</c:when>
			<c:when test="${article.categoryNo == 2}">
				<p>QnA</p>
			</c:when>
			<c:when test="${article.categoryNo == 3}">
				<p>채용공고</p>
			</c:when>
			<c:otherwise>
				<p>스터디</p>
			</c:otherwise>
		</c:choose>
	</h1>

	<form action='<c:url value='/app/article/update?no=${article.no}'/>' method='post'>
		<input type='hidden' name='no' value='${article.no}'><br>
		<input type='text' class="form-control" name='title' value='${article.title}'><br>
    <script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
    <input type="text" name="content" id="content" hidden='hidden'/>
     <div id="editor">    
     </div>
        <script>
        var content = `${article.content}`;
        const editor = new toastui.Editor({
            el: document.querySelector('#editor'),
            previewStyle: 'vertical',
            height: '500px',
          });
        editor.setHtml(content);
    </script>
    <p>
      태그<br>
      <c:forEach items="${tags}" var="tag">
        <input class="form-check-input" type='checkbox' name='tagNo' value='${tag.no}'
          <c:forEach items="${article.tags}" var="articleTag">
          <c:if test="${articleTag.no == tag.no}">
            checked
          </c:if>
          </c:forEach>
        >${tag.name}
      <c:if test="${tag.no % 9== 0 }"><br></c:if>
    </c:forEach>
  </p>
  
		<button class="btn btn-primary" id="form-submit">수정</button>
	</form>
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