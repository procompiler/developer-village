package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.Article;
import com.devil.domain.Tag;
import com.devil.service.ArticleService;

@WebServlet("/article/list")
public class ArticleListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    ArticleService articleService = (ArticleService) ctx.getAttribute("articleService");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head><title>게시글목록</title>");
    out.println("<link rel=\"stylesheet\" type=\"text/css\" href='../style.css'></head>");
    out.println("<body>");
    try {
      out.println("<h1>게시물 목록</h1>");
      out.println("<button type='button' onclick=\"location.href='form'\">글쓰기</button>");

      List<Article> list = null;

      String keyword = request.getParameter("keyword");
      String keywordTitle = request.getParameter("keywordTitle");
      String keywordWriter = request.getParameter("keywordWriter");
      String keywordTag = request.getParameter("keywordTag");
      //String keywordCategory = request.getParameter("keywordCategory");

      if (keyword != null) {
        list = articleService.list(keyword);
      } else if (keywordTitle != null) {
        HashMap<String, Object> keywordMap = new HashMap<>();
        keywordMap.put("title", keywordTitle);
        keywordMap.put("writer", keywordWriter);
        keywordMap.put("tag", keywordTag);
        //keywordMap.put("category", keywordCategory);
        list = articleService.list(keywordMap);
      } else {
        list = articleService.list();
      }
      out.println("<table border='1'>");
      out.println("<thead>");
      out.println("<tr>" // table row
          + "<th>번호</th>" // table header
          + "<th>제목</th>" + "<th>작성자</th>" + "<th>등록일</th>" + "<th>조회수</th>" + "</tr>");
      out.println("</thead>");
      out.println("<tbody>");

      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      for (Article article : list) {
        
        out.printf(
            "<tr>" + "<td>%d</td>", article.getNo());
        List<Tag> tags = article.getTags();
        out.println("<td id='title'><ul id='tags'>");
        for (Tag tag : tags) {
          out.printf("<li id='color' style='background-color: #%s; color: #%s'>%s</li>" , tag.getTagColor(), tag.getFontColor(), tag.getName());
        }
        out.println("</ul>");
        out.printf("<a href='detail?no=%d'>%s</a></td>" + "<td>%s</td>" + "<td>%s</td>" + "<td>%d</td>", 
                article.getNo(), article.getTitle(), article.getWriter().getNickname(),
                formatter.format(article.getCreatedDate()), article.getViewCount());
      }
      out.println("</tbody>");
      out.println("</table>");

      out.println("<p>");
      
      out.println("<form action='list' method='get'>");
      out.printf("검색어: <input type='text' name='keyword' value='%s'>\n",
          keyword != null ? keyword : "");
      out.println("<button>검색</button>");
      out.println("</form>");
      out.println("</p>");

      out.println("<hr>");
      out.println("<h2>상세 검색</h2>");
      out.println("<p>");
      out.println("<form action='list' method='get'>");
      out.printf("제목: <input type='text' name='keywordTitle' value='%s'><br>\n",
          keywordTitle != null ? keywordTitle : "");
      out.printf("작성자: <input type='text' name='keywordWriter' value='%s'><br>\n",
          keywordWriter != null ? keywordWriter : "");
      out.printf("태그: <input type='text' name='keywordTag' value='%s'><br>\n",
          keywordTag != null ? keywordTag : "");
      //out.printf("카테고리: <input type='text' name='keywordMember' value='%s'><br>\n",
      //    keywordTag != null ? keywordTag : "");
      out.println("<button>검색</button>");
      out.println("</form>");
      out.println("</p>");
    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
      return;
    }
    out.println("</body>");
    out.println("</html>");
  }

}
