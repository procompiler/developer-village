package com.devil.web;

import java.sql.Date;
import java.util.Map;
import com.devil.domain.Article;
import com.devil.service.ArticleService;
import com.devil.util.Prompt;
import com.devil.web.filter.Command;

public class ArticleAddServlet implements Command {

  ArticleService articleService;

  public ArticleAddServlet(ArticleService articleService) {
    this.articleService = articleService;
  }

  @Override
  public void execute(Map<String,Object> context) {
    try {
      System.out.println("[게시글 등록]");
      Article article = new Article();

      article.setTitle(Prompt.inputString("제목? "));
      article.setContent(Prompt.inputString("내용? "));
      article.setContent(Prompt.inputString("작성자 번호? "));
      article.setCategoryNo(Prompt.inputInt("카테고리 번호? (1: 커뮤니티, 2:QA, 3.채용공고, 4.스터디"));
      switch (article.getCategoryNo()) {
        case 1: // 커뮤니티
          break;
        case 2: // QA
          break;
        case 3:
          article.setEndDate(Date.valueOf(Prompt.inputString("모집마감일? " )));
          break;
        case 4:
          break;
      }
      articleService.add(article);

    }catch (Exception e) {
      e.printStackTrace();
    }
  }
}