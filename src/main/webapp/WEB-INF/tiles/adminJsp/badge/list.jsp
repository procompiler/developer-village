<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="row">
  <h1>뱃지 목록</h1>
  <div class="col-sm-6">
	<a class='btn btn-primary' href='form'>뱃지 추가</a>
  <div id="search-bar">
  <span style="float: left">뱃지 검색</span>
  <form action='list' method='get' autocomplete="off">
  <input id="search" type="search" name='keyword' 
  value='${param.keyword}' placeholder="검색어 입력.." style=" padding-left: 40px; margin-right: 30px;">
  <button class="btn btn-primary">검색</button>
  </form>
  </div>
</div>

<div class="row col-sm-10 g-3">
  <c:forEach items="${badgeList}" var="b">
    <div class="col">
      <div class="card" style="width: 14rem;">
        <div class="card-band" style="background-color: ${t.tagColor}"></div>
        <div class="card-body">
          <h5 class="card-title">
            <a href='${b.no}'>#${b.name}</a>
          </h5>
          <img style="float: right;"
            src='../../upload/badge/${b.photo}_80x80.png' alt='${b.name}'>
          <a class="btn btn-outline-primary" href='${b.no}'>뱃지 수정</a>
          <c:if test="${b.state == 0}">
            <p style="color:red;">삭제됨</p>
          </c:if>
        </div>
      </div>
    </div>
  </c:forEach>
</div>

</div>