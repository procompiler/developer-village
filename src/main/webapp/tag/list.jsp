<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.devil.domain.Tag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>커뮤니티</title>
<jsp:include page="/header.jsp"></jsp:include>
<h1>커뮤니티</h1>
<div class="row row-cols-1 row-cols-md-3 g-4">
  <c:forEach items="${tagList}" var="t">
    <div class="col">
      <div class="card" style="width: 15rem;">
        <div class="card-band" style="background-color: ${t.tagColor}"></div>
        <div class="card-body">
          <h5 class="card-title">
            <a href='../article/list?tagNo=${t.no}'>#${t.name}</a>
          </h5>
          <img style="float: right;"
            src='../../upload/tag/${t.photo}_80x80.png' alt='${t.name}'>
          <c:choose>
            <c:when test="${t.followed}">
              <a class="btn btn-outline-danger"
                href="../follow/deleteTag?followeeNo=${t.no}">언팔로우</a>
            </c:when>
            <c:otherwise>
              <a class="btn btn-primary"
                href="../follow/addTag?followeeNo=${t.no}">팔로우</a>
            </c:otherwise>
          </c:choose>
        </div>
      </div>
    </div>
  </c:forEach>
</div>
<jsp:include page="/footer.jsp"></jsp:include>
