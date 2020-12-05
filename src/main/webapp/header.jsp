<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String contextPath = request.getServletContext().getContextPath();
%>

<nav>
	<ul>
		<li><a href='<%=contextPath%>'><img id="logo-nav" src="../upload/devil.png" /></a></li>
		<div id="sitename">
		  <a href="../index.html">
			<li><span class="main-font-color">D</span>eveloper</li>
			<li><span class="main-font-color">V</span>illage</li>
			</a>
		</div>
		<div class="buttons">
			<li><i class="fas fa-user-friends"></i><a href='<%=contextPath%>/article/list?categoryNo=1'>커뮤니티</a></li>
			<li><i class="fas fa-question"></i><a href='<%=contextPath%>/article/list?categoryNo=2'>질문</a></li>
			<li><i class="far fa-building"></i><a href='<%=contextPath%>/article/list?categoryNo=3'>채용공고</a></li>
			<li><i class="fas fa-pencil-alt"></i><a href='<%=contextPath%>/article/list?categoryNo=4'>스터디</a></li>
			</div>
		</div>
		<hr>
		<div class="buttons">
			<li><i class="fas fa-tags"></i><a href="<%=contextPath%>/tag/list">태그</a></li>
			<div id="tag">
				<li><i class="fas fa-hashtag"></i><a href="#">Java</a></li>
				<li><i class="fas fa-hashtag"></i><a href="#">JavaScript</a></li>
				<li><i class="fas fa-hashtag"></i><a href="#">Ruby</a></li>
				<li><i class="fas fa-hashtag"></i><a href="#">Kotlin</a></li>
			</div>
		</div>
	</ul>
</nav>
<header>
	<div style="float: left;">
		<form autocomplete="off">
			<input id="search" type="search" placeholder="검색어 입력.."
				style="padding-left: 40px;">
		</form>
	</div>
	<div>
		<button type='button' id="head-button"
			onclick="location.href='<%=contextPath%>/user/list'">유저목록</button>
		<button type='button' id="head-button"
			onclick="location.href='<%=contextPath%>/auth/login'">로그인</button>
		<button type='button' id="head-button"
			onclick="location.href='<%=contextPath%>/article/form'">글쓰기</button>
	</div>
</header>

<div class='content'>
