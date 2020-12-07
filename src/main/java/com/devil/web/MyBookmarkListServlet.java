package com.devil.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.User;
import com.devil.service.ArticleService;

@WebServlet("/mypage/bookmarklist")
public class MyBookmarkListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    ArticleService articleService = (ArticleService)request.getServletContext().getAttribute("articleService");
    try {
      request.setAttribute("bookmarkList", articleService.list((User)request.getSession().getAttribute("loginUser")));
      request.getRequestDispatcher("/mypage/bookmarklist.jsp").include(request, response);
    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
  }
}
