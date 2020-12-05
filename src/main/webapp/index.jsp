<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <title>Devil sample page</title>
</head>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
  integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />

<body>
  <nav>
    <ul>
      <div id="logo">
        <li><img src="devil.png" /></li>
      </div>
      <div id="sitename">
        <li class="s-font"><span style="color: #6C5DDF;">D</span>eveloper</li>
        <li class="s-font"><span style="color: #6C5DDF;">V</span>illage</li>
      </div>
      <div class="buttons">
        <li><i class="fas fa-user-friends"></i><a href="article/list?categoryNo=1">커뮤니티</a></li>
        <li><i class="fas fa-question"></i><a href="article/list?categoryNo=2">질문</a></li>
        <li><i class="far fa-building"></i><a href="article/list?categoryNo=3">채용공고</a></li>
        <li><i class="fas fa-pencil-alt"></i><a href="article/list?categoryNo=4">스터디</a></li>
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
        <input id="search" type="search" placeholder="검색어 입력.." style="padding-left: 40px;">
      </form>
    </div>
    <div >
    <button type='button' id="head-button" onclick="location.href='user/list'">회원관리</button>
      <button type='button' id="head-button" onclick="location.href='auth/login'">로그인</button>
      <button type='button' id="head-button" onclick="location.href='article/form'">글쓰기</button>
      <button type='button' id="head-button" onclick="location.href='user/form.html'">회원가입</button>
    </div>
  </header>
  <article>
    <h1>자바를 자바자바</h1>
    <p class="date">2020-10-20</p>
    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem
      ipsum dolor sit amet, consectetur adipiscing elitLorem ipsum dolor
      sit amet, consectetur adipiscing elitLorem ipsum dolor sit amet,
      consectetur adipiscing elitLorem ipsum dolor sit amet, consectetur
      adipiscing elitLorem ipsum dolor sit amet, consectetur adipiscing
      elitLorem ipsum dolor sit amet, consectetur adipiscing elitLorem
      ipsum dolor sit amet, consectetur adipiscing elitLorem ipsum dolor
      sit amet, consectetur adipiscing elit</p>
    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem
      ipsum dolor sit amet, consectetur adipiscing elitLorem ipsum dolor
      sit amet, consectetur adipiscing elitLorem ipsum dolor sit amet,
      consectetur adipiscing elitLorem ipsum dolor sit amet, consectetur
      adipiscing elitLorem ipsum dolor sit amet, consectetur adipiscing
      elitLorem ipsum dolor sit amet, consectetur adipiscing elitLorem
      ipsum dolor sit amet, consectetur adipiscing elitLorem ipsum
      sit amet, consectetur adipiscing elit</p>
    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem
      ipsum dolor sit amet, consectetur adipiscing elitLorem ipsum dolor
      sit amet, consectetur adipiscing elitLorem ipsum dolor sit amet,
      consectetur adipiscing elitLorem ipsum dolor sit amet, consectetur
      adipiscing elitLorem ipsum dolor sit amet, consectetur adipiscing
      elitLorem ipsum dolor sit amet, consectetur adipiscing elitLorem
      ipsum dolor sit amet, consectetur adipiscing elitLorem ipsum
      sit amet, consectetur adipiscing elit</p>
  </article>
  <div id="comment" class="content">
    <ul>
      <li>댓글댓글댓글댓글</li>
      <li>댓글댓글댓글댓글</li>
      <li>댓글댓글댓글댓글</li>
      <li>댓글댓글댓글댓글</li>
    </ul>
  </div>
  <footer>
    <hr>
    <div style="float: left; margin-bottom: 40px; margin-right: 30px; margin-top: 30px;">
      <img src="devil.png" />
    </div>
    <div style="margin-top: 30px; text-align:left;">
      <b>Developer’s Village</b><span style="float:right; margin-right: 30px;">Made with love and <span
          style="color: #6C5DDF;">Procompiler</span></span><br>
      Devil Community Copy right 2020<span style="float:right; margin-top: 10px; margin-right: 30px;"><img
          id="pro-logo" src="procompiler.png" /></span><br>
      문의 : test1@gmail.com<br>
      주소 : 서울시 어쩌구 어쩌라고 180-28 오호라빌딩 27층<br>
      대표명 : 유관순
    </div>

  </footer>
</body>

</html>