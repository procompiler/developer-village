package com.devil.web.filter;

import java.util.List;
import java.util.Map;
import com.devil.dao.ArticleDao;
import com.devil.domain.Article;

public class ArticleListServlet implements Command{

  ArticleDao articleDao;

  public ArticleListServlet(ArticleDao articleDao) {
    this.articleDao = articleDao;
  }

  @Override
  public void execute(Map<String,Object> context) {
    System.out.println("[게시물 목록]");
    try {
      System.out.println("번호, 제목, 작성자, 등록일, 조회수");
      List<Article> list = articleDao.findAll(null);
      for (Article article : list) {
        System.out.printf("%d, %s, %s, %s, %d\n",
            article.getNo(),
            article.getTitle(),
            article.getWriter().getName(),
            article.getCreatedDate(),
            article.getViewCount());
      }
    } catch (Exception e) {
      System.out.println("게시글 목록 조회 중 오류 발생!");
      e.printStackTrace();
    }
  }
}
