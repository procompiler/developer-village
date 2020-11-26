package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.Article;
import com.devil.domain.Comment;
import com.devil.service.ArticleService;

@WebServlet("/article/detail")
public class ArticleDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

      // Servlet container에 들어 있는 ArticleService를 꺼낸다.
      ServletContext ctx = request.getServletContext();
      ArticleService articleService = (ArticleService) ctx.getAttribute("articleService");

      // 웹주소에 동봉된 데이터(Query String: qs)를 읽는다.
      // 클라이언트가 URL에 데이터를 포함해서 보낸다.
      // 숫자 데이터가 넘어오기 때문에 깨질 염려가 없다.
      int no = Integer.parseInt(request.getParameter("no"));

      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head><title>게시글 조회</title>");
      out.println("<link rel=\"stylesheet\" type=\"text/css\" href='../style.css'></head>");
      out.println("<body>");

      try {
        out.println("<h1>[게시물 조회]</h1>");

        Article article = articleService.get(no);

        if (article == null) {
          out.println("해당 번호의 게시글이 없습니다.");
          return;
        }
        System.out.println(article.getTitle());

        out.printf("<p>제목: %s</p>", article.getTitle());
        out.printf("<p>작성자: %s</p>", article.getWriter().getNickname());
        out.printf("<p>카테고리: %s</p>", article.getCategoryNo());
        out.printf("<p>등록일: %s</p>", article.getCreatedDate());
        out.printf("<p>조회수: %d</p>", article.getViewCount());
        out.printf("<p>내용: %s</p>", article.getContent());

        for (Comment comment : article.getComments()) {
          out.printf("<p>댓글작성자: %s</p>", comment.getWriter().getNickname());
          out.printf("<p>댓글내용: %s</p>", comment.getContent());

        }
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


