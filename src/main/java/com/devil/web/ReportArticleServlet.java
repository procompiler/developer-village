package com.devil.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Article;
import com.devil.service.ArticleService;

@Controller
public class ReportArticleServlet {
  ArticleService articleService;

  public ReportArticleServlet(ArticleService articleService) {
    this.articleService = articleService;
  }

  @RequestMapping("/report/reportArticle")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    response.setContentType("text/html;charset=UTF-8");

    Article reportedArticle = articleService.get(Integer.parseInt(request.getParameter("no")));
    request.setAttribute("reportedArticle", reportedArticle);

    return "/report/report-article.jsp";

  }
}
