package com.devil.handler;

import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import com.devil.dao.ArticleDao;
import com.devil.domain.Article;
import com.devil.util.Prompt;

public class ArticleListCommand implements Command{

  ArticleDao articleDao;

  public ArticleListCommand(ArticleDao articleDao) {
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
            article.getRegisteredDate(),
            article.getViewCount());
      }
    } catch (Exception e) {
      System.out.println("게시글 목록 조회 중 오류 발생!");
      e.printStackTrace();
    }
  }

  
  public String parseDate(String rawDate) {
    if (!rawDate.split(" ")[0].equals("~")) {
      return "1996-09-02";
    }
    return "2020-" + rawDate.split(" ")[1].split("\\(")[0].replace("/", "-");
  }
}
