<%@page import="com.devil.domain.Article"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.devil.domain.Tag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h1 style="margin-left: 70px; font-weight: bold">커뮤니티</h1>

<div class="d-grid gap-3"/>
<c:forEach items="${articleList}" var="a" begin="0" end="5">
 <div class="card">
    <a class="btn position-absolute top-0 end-0"
      href='delete?articleNo=${a.no}'><i
      class="fas fa-minus-circle text-danger fa-lg shadow"></i></a>
    <div class="row g-0">
      <div class="col-sm-2">
        <div class="text-center mr-2 mt-4">
          <a href='../user/detail?no=${a.writer.no}'><img
            class="card-img-top rounded-circle"
            src='../upload/user/${a.writer.photo}_60x60.jpg'
            alt='[${a.writer.nickname}]' /></a><br> <small>${a.writer.nickname}</small>
        </div>
      </div>
      <div class="col-sm-10">
        <div class="card-body">
          <div style="height: 40px;">
            <c:forEach items="${a.tags}" var="tag">
              <span class="badge"
                style="background-color: ${tag.tagColor}; color: ${tag.fontColor}">${tag.name}</span>
            </c:forEach>
          </div>
          <div class="float-start">
          <h5 class='card-title'>
            <a href='detail?no=${a.no}'>${a.title}</a>
          </h5>
          <p class="card-text">
            <small class="text-muted"><fmt:formatDate
                value="${a.createdDate}" pattern="yyyy.MM.dd" /></small></p>
          </div>
          <div class="position-absolute bottom-0 end-0 mx-3">
            <ul class="list-inline mb-0">
              <li class="list-inline-item"><i class="far fa-comment mr-2"></i></li>
              <li class="list-inline-item">${a.commentCount}</li>
              <li class="list-inline-item"><i class="fas fa-eye mr-2"></i></li>
              <li class="list-inline-item">${a.viewCount}</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</c:forEach>
</div>
</div>
