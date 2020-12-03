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
    try {
      List<Article> list = null;

      String keyword = request.getParameter("keyword");
      String keywordTitle = request.getParameter("keywordTitle");
      String keywordWriter = request.getParameter("keywordWriter");
      String keywordTag = request.getParameter("keywordTag");
      //String keywordCategory = request.getParameter("keywordCategory");

      if (keyword != null) {
        list = articleService.list(keyword);
      } else if (keywordTitle != null) {
        HashMap<String, Object> keywordMap = new HashMap<>();
        keywordMap.put("title", keywordTitle);
        keywordMap.put("writer", keywordWriter);
        keywordMap.put("tag", keywordTag);
        //keywordMap.put("category", keywordCategory);
        list = articleService.list(keywordMap);
      } else {
        list = articleService.list();
      }
      request.setAttribute("list", list);
      request.getRequestDispatcher("/article/list.jsp").include(request, response);

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
      return;
    }
    out.println("</body>");
    out.println("</html>");
  }

}
