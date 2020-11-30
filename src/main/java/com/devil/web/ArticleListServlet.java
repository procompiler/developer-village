package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
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
      out.println("<button type='button' onclick=\"location.href='form.html'\">글쓰기</button>");

      List<Article> list = articleService.list(null);
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

    } catch (Exception e) {
      out.printf("<p>작업 처리 중 오류 발생! - %s</p>\n", e.getMessage());

      StringWriter errOut = new StringWriter();
      e.printStackTrace(new PrintWriter(errOut));

      out.printf("<pre>%s</pre>\n", errOut.toString());
    }
    out.println("</body>");
    out.println("</html>");
  }

}
