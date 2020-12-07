<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Keywords" content="비밀번호 변경 확인" />
<meta name="Description" content="비밀번호 변경 확인" />
<link rel="stylesheet" href="../resources/css/screen.css" type="text/css" media="screen" />
<title>비밀번호가 변경확인</title>
<script type="text/javascript">
//<![CDATA[
//]]>
</script>          
</head>
<body>
 
<div id="wrap">
 
    <div id="header">
    <jsp:include page="/header.jsp"></jsp:include>
    </div>
 
    <div id="main-menu">
        <%@ include file="../inc/main-menu.jsp" %>
    </div>
 
    <div id="container">
        <div id="content" style="min-height: 800px;">
            <div id="url-navi">회원</div>
 
<!-- 본문 시작 -->
<h1>비밀번호가 변경되었습니다.</h1>
변경된 비밀번호로 다시 로그인하실 수 있습니다.<br />
<input type="button" value="로그인" onclick="javascript:location.href='login'" />
<!--  본문 끝 -->
 
        </div><!-- content 끝 -->
    </div><!--  container 끝 -->
   
    <div id="sidebar">
        <%@ include file="loginUsers-menu.jsp" %>
    </div>
   
    <div id="extra">
        <%@ include file="../inc/extra.jsp" %>
    </div>
 
    <div id="footer">
        <%@ include file="../inc/footer.jsp" %>
    </div>
 
</div>
 
</body>
</html>