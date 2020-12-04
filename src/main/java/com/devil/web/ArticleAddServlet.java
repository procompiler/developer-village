package com.devil.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.devil.domain.Article;
import com.devil.domain.Tag;
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

    Article article = new Article();
    article.setTitle(request.getParameter("title"));
    article.setContent(request.getParameter("content"));
    article.setCategoryNo(Integer.parseInt(request.getParameter("categoryNo")));

    HttpSession session = request.getSession();

    try {
      User loginUser = (User) session.getAttribute("loginUser");
      article.setWriter(loginUser);
      List<Tag> tags = new ArrayList<>();
      String[] tagNoList = request.getParameterValues("tags");
      if (tagNoList != null) {
        for (String tagNo : tagNoList) {
          tags.add(new Tag().setNo(Integer.parseInt(tagNo)));
        }
      }
      article.setTags(tags);
      articleService.add(article);
      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
  }
}