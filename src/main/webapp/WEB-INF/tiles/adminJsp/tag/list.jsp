<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="row">
<h1>태그 관리</h1>

<div class="col-sm-6">
<a class='btn btn-primary' href='form'>태그 추가</a>
  <div id="search-bar">
  <span style="float: left">태그 검색</span>
  <form action='${contextPath}?' method='get' autocomplete="off">
  <input id="search" name="keyword" value='${param.keyword}' type="text" placeholder="태그 이름 검색.." style=" padding-left: 40px; margin-right: 30px;">
  <button class="btn btn-primary">검색</button>
  </form>
  </div>
</div>

<div class="row col-sm-10 g-3">
	<c:forEach items="${tagList}" var="t">
		<div class="col">
			<div class="card" style="width: 14rem;">
				<div class="card-band" style="background-color: ${t.tagColor}"></div>
				<div class="card-body">
					<h5 class="card-title">
						<a href='${t.no}'>#${t.name}</a>
					</h5>
					<img style="float: right;" class="rounded-circle" 
						src='../../upload/tag/${t.photo}_80x80.png' alt='${t.name}'>
					<a class="btn btn-outline-primary" href='${t.no}'>태그 수정</a>
					<c:if test="${t.state == 0}">
						<p style="color:red;">삭제됨</p>
					</c:if>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
</div>