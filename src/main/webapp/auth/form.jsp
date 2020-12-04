<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>로그인</title>
<link rel="stylesheet" type="text/css" href='../style.css'>
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
</head>
<body>
	<h1>로그인</h1>
	<form action='login' method='post'>
		이메일: <input type='email' name='email'
			value='<%=request.getAttribute("email")%>'><br> 암호: <input
			type='password' name='password'><br> <input
			type='checkbox' name='saveEmail'> 이메일 저장<br>
		<button>로그인</button>
	</form>
</body>
</html>
