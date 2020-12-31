<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="auth">
	<form id="loginForm" action='login' method='post'>
  <div style="text-align: center">
	 <img id="logo-nav" src="/developer-village/upload/devil.png" style="width: 120px;"/>
		<p style="margin-top: 30px; margin-bottom: 1rem; font-size: 28px; font-weight: bold;">
		로그인을 해주세요!</p>
		<p><span class="main-color">데빌</span>은 국내최대 개발자 커뮤니티입니다.</p>
		</div>
		  <div class="form-floating mb-3">
          <input id="inputEmail" type="email" class="form-control" name='email' placeholder="name@example.com">${user.email}
          <label for="floatingInput">Email address</label>
      </div>
      
    <div class="form-floating">
        <input id="inputPassword" type="password" class="form-control" name='password' placeholder="Password">${user.password}
        <label for="floatingPassword">Password</label>
    </div>
    <div class="form-check" style="margin-top: 30px;">
      <input class="form-check-input" type="checkbox" name='saveEmail' id="defaultCheck1">
      <label class="form-check-label" for="defaultCheck1" style="text-align: left;">
      이메일 저장
      </label>
		</div>
		<div class="d-grid gap-2">
     <button class="btn btn-primary">로그인</button>
    </div>
    
    <div class="g-signin2" data-onsuccess="onSignIn"></div>
        <a class="btn btn-primary" href="https://github.com/login/oauth/authorize?client_id=71a8f9b1c58cc9318bc3&redirect_uri=http:/localhost:8080/developer-village/"
      >GitHub 아이디로 로그인</a>
    
    <div style="text-align:center;">
		<a href="searchId">이메일 계정 찾기</a>
		<span>  |  </span>
		<a href="searchPwd">비밀번호 재설정</a>
		<span> | </span>
		<a href="../user/form">회원가입</a>
		</div>
	</form>
</div>

<script>
  document.querySelector("#loginForm").onsubmit = () => {
    if (document.querySelector("#inputEmail").value.length < 1 ||
        document.querySelector("#inputPassword").value.length < 1 ) {
        alert("입력하지 않은 항목이 있습니다.")
        return false;
      }
    };
</script>

<script src="https://apis.google.com/js/platform.js" async defer></script>
<script>
function autoServerLogin(accessToken) {
    location.href = "googleLogin?accessToken=" + accessToken;
}

function onSignIn(googleUser) {
	  var profile = googleUser.getBasicProfile();
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
	}

// 구글 로그아웃 코드
function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
  }
</script>

