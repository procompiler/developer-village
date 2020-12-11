package com.devil.web;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.Tag;
import com.devil.service.ArticleService;
import com.devil.service.TagService;

@WebServlet("/community/articlelist")
public class CommunityArticleListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    ArticleService articleService = (ArticleService) ctx.getAttribute("articleService");
    TagService tagService = (TagService) ctx.getAttribute("tagService");

    response.setContentType("text/html;charset=UTF-8");

    try {
      Tag tag = tagService.get(Integer.parseInt(request.getParameter("tagNo")));
      request.setAttribute("tag", tag);
      request.setAttribute("articleList", articleService.listByTagNo(tag.getNo()));

      request.getRequestDispatcher("/community/articlelist.jsp").include(request, response);


    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
  }

}
