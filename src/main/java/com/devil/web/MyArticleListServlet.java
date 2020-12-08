package com.devil.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.Article;
import com.devil.domain.User;
import com.devil.service.ArticleService;

@WebServlet("/mypage/articlelist")
public class MyArticleListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    ArticleService articleService = (ArticleService)request.getServletContext().getAttribute("articleService");
    try {
      request.setAttribute("articleList", articleService.list((User)request.getSession().getAttribute("loginUser")));
      request.getRequestDispatcher("/mypage/articlelist.jsp").include(request, response);
      for (Article a : (List<Article>) request.getAttribute("articleList")) {
        System.out.println(a.getTitle());
      }
    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
  }
}
