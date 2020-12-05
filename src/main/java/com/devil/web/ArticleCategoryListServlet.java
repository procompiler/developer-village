package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.Article;
import com.devil.service.ArticleService;

@WebServlet("/article/categoryNo")
public class ArticleCategoryListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    ArticleService articleService = (ArticleService) ctx.getAttribute("articleService");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    try {
      int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
      List<Article> list = null;

      String keyword = request.getParameter("keyword");
      String keywordTitle = request.getParameter("keywordTitle");
      String keywordWriter = request.getParameter("keywordWriter");
      String keywordTag = request.getParameter("keywordTag");

      if (keyword != null) {
        list = articleService.list(keyword);
      } else if (keywordTitle != null) {
        HashMap<String, Object> keywordMap = new HashMap<>();
        keywordMap.put("title", keywordTitle);
        keywordMap.put("writer", keywordWriter);
        keywordMap.put("tag", keywordTag);
        list = articleService.list(keywordMap);
      } else {
        list = articleService.list();
      }
      request.setAttribute("list", list);
      request.setAttribute("categoryNo", categoryNo);
      request.getRequestDispatcher("/article/list.jsp").include(request, response);

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
    out.println("</body>");
    out.println("</html>");
  }



}
