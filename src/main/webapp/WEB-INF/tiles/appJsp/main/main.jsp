<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.devil.domain.Tag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h1>커뮤니티</h1>
<div class="row row-cols-1 row-cols-md-3 g-4">
  <c:forEach items="${tagList}" var="t">
    <div class="col">
      <div class="card" style="width: 15rem;">
        <div class="card-band" style="background-color: ${t.tagColor}"></div>
        <div class="card-body">
          <h5 class="card-title">
            <a href='article/list?tagNo=${t.no}'>#${t.name}</a>
          </h5>
          <img style="float: right;"
            src='../upload/tag/${t.photo}_80x80.png' alt='${t.name}'>
        </div>
      </div>
    </div>
  </c:forEach>
