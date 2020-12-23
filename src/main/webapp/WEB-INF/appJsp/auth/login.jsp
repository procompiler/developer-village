<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>로그인</title>
  <jsp:include page="../header.jsp"></jsp:include>

</head>
<body>
<div id="login">
	<form action='login' method='post'>
	 <img id="logo-nav" src="/developer-village/upload/devil.png" style="width: 120px;"/>
		<p style="margin-top: 30px; margin-bottom: 1rem; font-size: 28px; font-weight: bold;">
		로그인을 해주세요!</p>
		<p>데빌은 국내최대 개발자 커뮤니티입니다.</p>
		  <div class="form-floating mb-3">
          <input type="email" class="form-control" id="floatingInput" name='email' placeholder="name@example.com">${user.email}
          <label for="floatingInput">Email address</label>
      </div>
      
    <div class="form-floating">
        <input type="password" class="form-control" id="floatingPassword" name='password' placeholder="Password">${user.password}
        <label for="floatingPassword">Password</label>
    </div>
			<input type='checkbox' name='saveEmail'> 이메일 저장<br>
		<button>로그인</button>
	</form>
	</div>
</body>
<jsp:include page="../footer.jsp"></jsp:include>
</html>
