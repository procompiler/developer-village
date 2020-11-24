package com.devil.web.filter;

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
    out.println("<head><title>게시글목록</title></head>");
    out.println("<style> table { border-top: 1px solid #444444; border-collapse: collapse;}" + 
    		"  th, td { border-bottom: 1px solid #444444; padding: 10px;} </style>");
    out.println("<body>");
    try {
      out.println("<h1>게시물 목록</h1>");

      List<Article> list = articleService.list(null);
      out.println("<table border='1'>");
      out.println("<tr>" // table row
          + "<th>번호</th>" // table header
          + "<th>제목</th>" + "<th>작성자</th>" + "<th>등록일</th>" + "<th>조회수</th>" + "</tr>");

      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      for (Article article : list) {
        out.printf(
            "<tr>" + "<td>%d</td>" + "<td>%s</td>"+ "<td>%s</td>" + "<td>%s</td>" + "<td>%d</td>"
                + "</tr>\n",
            article.getNo(), article.getTitle(), article.getWriter().getNickname(),
            formatter.format(article.getCreatedDate()), article.getViewCount());
      }
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
