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
import com.devil.domain.User;
import com.devil.service.ArticleService;

@WebServlet("/article/add")
public class ArticleAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    ArticleService articleService = (ArticleService) ctx.getAttribute("articleService");

    request.setCharacterEncoding("UTF-8");

    Article article = new Article();
    article.setTitle(request.getParameter("title"));
    article.setContent(request.getParameter("content"));


    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    // 웹브라우저 제목에 출력될 내용
    out.println("<head>");
    out.println("<meta http-equiv='Refresh' content='2;url=list'>");
    out.println("<title>게시글 등록</title></head>");
    out.println("<body>");

    try {
      out.println("<h1>[게시물 등록]</h1>");

      request.setCharacterEncoding("UTF-8");
      article.setTitle(request.getParameter("title"));
      article.setContent(request.getParameter("content"));
      article.setCategoryNo(Integer.parseInt(request.getParameter("categoryNo")));

//      switch (article.getCategoryNo()) {
//        case 1: // 커뮤니티
//          break;
//        case 2: // QA
//          break;
//        case 3:
//          article.setEndDate(Date.valueOf(Prompt.inputString("모집마감일? " )));
//          break;
//        case 4:
//          break;
//      }

      User loginUser = new User();
      loginUser.setNo(2);
//      loginUser.setNickname("깃허브망령");
      article.setWriter(loginUser);


      articleService.add(article);

      out.println("게시글을 등록했습니다.");

    }catch (Exception e) {
      out.printf("<p>작업 처리 중 오류 발생! - %s</p>\n", e.getMessage());
      StringWriter errOut = new StringWriter();
      e.printStackTrace(new PrintWriter(errOut));
      out.printf("<pre>%s</pre>\n", errOut.toString());
    }
    out.println("</body>");
    out.println("</html>");
  }
}