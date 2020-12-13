<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뱃지 추가</title>
<link rel="stylesheet"
  href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
  integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
  crossorigin="anonymous" />
  
  <link rel="stylesheet"
  href="../../node_modules/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href='../../style.css'>
</head>
<body>
  <jsp:include page="/header.jsp"></jsp:include>
  
<h1>뱃지 등록</h1>
<form action="add" method="post" enctype="multipart/form-data">
이름 : <input type="text" name="name"><br>
내용 : <textarea name="content" cols="60" rows="10"></textarea><br>
뱃지 사진 : <input type="file" name="photo"><br>
<button class="btn btn-primary">등록</button>
</form>

  <jsp:include page="/footer.jsp"></jsp:include>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>