<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />

<nav>
	<ul>
		<li><img id="logo-nav" src="../upload/devil.png" /></li>
		<div id="sitename">
			<li><span class="main-font-color">D</span>eveloper</li>
			<li><span class="main-font-color">V</span>illage</li>
		</div>
		<div class="buttons">
			<li><i class="fas fa-user-friends"></i><a href="article/list">커뮤니티</a></li>
			<li><i class="fas fa-question"></i><a href="#">질문</a></li>
			<li><i class="far fa-building"></i><a href="#">채용공고</a></li>
			<li><i class="fas fa-pencil-alt"></i><a href="#">스터디</a></li>
		</div>
		<hr>
		<div class="buttons">
			<li><i class="fas fa-tags"></i><a href="tag/list">태그</a></li>
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
			onclick="location.href='user/list'">유저목록</button>
		<button type='button' id="head-button"
			onclick="location.href='auth/login.html'">로그인</button>
		<button type='button' id="head-button"
			onclick="location.href='article/form.html'">글쓰기</button>
	</div>
</header>

</html>