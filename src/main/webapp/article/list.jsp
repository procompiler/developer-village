<%@page import="com.devil.domain.Tag"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.devil.domain.Article"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>게시글목록</title>
<link rel="stylesheet" type="text/css" href='../style.css'>
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
</head>

<body>
  <jsp:include page="/header"></jsp:include>
	<%SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");%>
	<%List<Article> list = (List<Article>) request.getAttribute("list");%>
  <%int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));%>

<%String categoryName = null;%>
<%switch (categoryNo) {
  case 1: categoryName = "커뮤니티"; break;
  case 2: categoryName = "QnA"; break;
  case 3: categoryName = "채용공고"; break;
  default :categoryName = "스터디"; break;
}%>
         
	<h1><%=categoryName%></h1>
	<button type='button' onclick="location.href='form'">글쓰기</button>

	<table border='1'>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>등록일</th>
				<th>조회수</th>
				<th>상태</th>
			</tr>
		</thead>
		<tbody>
			<%for (Article article : list) {%>
			<%if(article.getCategoryNo() == categoryNo) {%>

			<tr>
				<td><%=article.getNo()%></td>
				<td id='title'>
					<ul id='tags'>
						<%
						  List<Tag> tags = article.getTags();
						for (Tag tag : tags) {
						%>
						<li id='color'
							style="background-color: #<%=tag.getTagColor()%>; color: #<%=tag.getFontColor()%>;"><%=tag.getName()%></li>
						<%
						  }
						%>
					</ul><a href='detail?no=<%=article.getNo()%>'><%=article.getTitle()%></a>
				</td>
				<td><%=article.getWriter().getNickname()%></td>
				<td><%=formatter.format(article.getCreatedDate())%></td>
				<td><%=article.getViewCount()%></td>
				<td><%=article.getState() == 1 ? "" : "삭제된 게시글"%></td>
			</tr>
			<%}%>
			<%}%>
		</tbody>
	</table>
	<p>
		<%
		  String keyword = request.getParameter("keyword");
		%>
	
	<form action='list' method='get'>
		검색어: <input type='text' name='keyword'
			value='<%=keyword != null ? keyword : ""%>'>
		<button>검색</button>
	</form>
	</p>
	<hr>
	<h2>상세 검색</h2>
	<p>
		<%
		String keywordTitle = request.getParameter("keywordTitle");
		String keywordWriter = request.getParameter("keywordWriter");
		String keywordTag = request.getParameter("keywordTag");
		%>
	
	<form action='list' method='get'>
		제목: <input type='text' name='keywordTitle'
			value='<%=keywordTitle != null ? keywordTitle : ""%>'><br>
		작성자: <input type='text' name='keywordWriter'
			value='<%=keywordWriter != null ? keywordWriter : ""%>'><br>
		태그: <input type='text' name='keywordTag'
			value='<%=keywordTag != null ? keywordTag : ""%>'><br>
		<button>검색</button>
	</form>
	</p>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>
