package com.devil.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.Article;
import com.devil.domain.Comment;
import com.devil.service.ArticleService;
import com.devil.service.CommentService;

@WebServlet("/comment/list")
public class CommentListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    ArticleService articleService = (ArticleService) ctx.getAttribute("articleService");
    CommentService commentService = (CommentService) ctx.getAttribute("commentService");

    int no = Integer.parseInt(request.getParameter("no"));

    response.setContentType("text/html;charset=UTF-8");
    //PrintWriter out = response.getWriter();

    try {
      Article article = articleService.get(no);
      request.setAttribute("article", article);

      List<Comment> comments = commentService.getByArticleNo(no);
      request.setAttribute("comments", comments);

      request.getRequestDispatcher("/comment/list.jsp").include(request, response);

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
  }
}
