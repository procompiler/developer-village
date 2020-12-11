package com.devil.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Article;
import com.devil.service.ArticleService;

@Controller
public class ArticleListController {
  ArticleService articleService;
public ArticleListController(ArticleService articleService) {
  this.articleService = articleService;
}
  
  @RequestMapping("/article/list")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    response.setContentType("text/html;charset=UTF-8");

      List<Article> articles = null;

      String keyword = request.getParameter("keyword");
      String keywordTitle = request.getParameter("keywordTitle");
      String keywordWriter = request.getParameter("keywordWriter");
      String keywordTag = request.getParameter("keywordTag");
      //String keywordCategory = request.getParameter("keywordCategory");

      if (keyword != null) {
        articles = articleService.list(keyword);
      } else if (keywordTitle != null) {
        HashMap<String, Object> keywordMap = new HashMap<>();
        keywordMap.put("title", keywordTitle);
        keywordMap.put("writer", keywordWriter);
        keywordMap.put("tag", keywordTag);
        //keywordMap.put("category", keywordCategory);
        articles = articleService.list(keywordMap);
      } else {
        articles = articleService.list();
      }

      request.setAttribute("articles", articles);

      return "/article/list.jsp";
    }
  }

