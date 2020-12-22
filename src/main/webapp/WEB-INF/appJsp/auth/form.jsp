<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>로그인</title>
  <jsp:include page="/header.jsp"></jsp:include>

</head>
<body>
	<h1>로그인</h1>
	<form action='login' method='post'>
	<div class="card-body">
	 <img id="logo-nav" src="/developer-village/upload/devil.png" style="width: 77px; text-align: center;"/>
		<div class="mb-3 row">
        <label for="email">Email</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="email" name='email'>${user.email}</textarea>
        </div>
      </div>
    <div class="mb-3 row">
      <label for="password">Password</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="password" name='password'>${user.password}</textarea>
      </div>
    </div>
			<input type='checkbox' name='saveEmail'> 이메일 저장<br>
		<button>로그인</button>
		</div>
	</form>
</body>
<jsp:include page="/footer.jsp"></jsp:include>
</html>
