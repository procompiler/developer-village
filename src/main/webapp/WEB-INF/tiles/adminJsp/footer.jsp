<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="appRoot" value="${pageContext.servletContext.contextPath}" />
</div>
<footer>
	<hr sytle="padding-top: 30px;">
	<div
		style="float: left; margin-bottom: 80px; margin-right: 30px; margin-top: 30px;">
		<img src="${appRoot}/upload/devil.png" />
	</div>
	<div style="margin-top: 30px; text-align: left;">
		<b>Developer’s Village</b> <span
			style="float: right; margin-right: 30px;"> Made with love and
			<span class="main-font-color">Procompiler</span>
		</span> <br>Devil Community Copy right 2020 <span
			style="float: right; margin-top: 10px; margin-right: 30px;"> <img
			id="pro-logo" src="${appRoot}/upload/procompiler.png" />
		</span> <br> 문의 : test1@gmail.com <br> 주소 : 서울시 어쩌구 어쩌라고 180-28
		오호라빌딩 27층 <br> 대표명 : 유관순
	</div>
</footer>
<script
	src="${appRoot}/node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>