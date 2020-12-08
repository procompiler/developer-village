package com.devil.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.Article;
import com.devil.domain.User;
import com.devil.service.ArticleService;
import com.devil.service.BookmarkService;

@WebServlet("/article/detail")
public class ArticleDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    ArticleService articleService = (ArticleService) ctx.getAttribute("articleService");
    BookmarkService bookmarkService = (BookmarkService) ctx.getAttribute("bookmarkService");

    response.setContentType("text/html;charset=UTF-8");

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Article article = articleService.get(no);
      if (article == null) {
        throw new Exception("해당 게시글이 없습니다.");
      }
      request.setAttribute("article", article);

      request.setAttribute("tags", article.getTags());

      Map<String, Object> map = new HashMap<>();
      map.put("userNo", ((User)request.getSession().getAttribute("loginUser")).getNo());
      map.put("articleNo", no);
      if (bookmarkService.get(map) != null) {
        request.setAttribute("bookmarked", true);
      } else {
        request.setAttribute("bookmarked", false);
      }

      request.getRequestDispatcher("/article/detail.jsp").include(request, response);

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
  }
}
