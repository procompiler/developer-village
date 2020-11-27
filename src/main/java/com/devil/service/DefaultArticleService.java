package com.devil.service;

import java.util.List;
import com.devil.dao.ArticleDao;
import com.devil.domain.Article;

public class DefaultArticleService implements ArticleService {
  ArticleDao articleDao;

  public DefaultArticleService(ArticleDao articleDao) {
    this.articleDao = articleDao;
  }

  @Override
  public int add(Article article) throws Exception {
    return articleDao.insert(article);
  }

  @Override
  public List<Article> list(String keyword) throws Exception {
    return articleDao.findAll(keyword);
  }

  @Override
  public Article get(int no) throws Exception {
    Article article = articleDao.findByNo(no);
    if (article != null) {
      articleDao.updateViewCount(no);
    }
    return article;
  }

  @Override
  public int update(Article article) throws Exception {
    return articleDao.update(article);
  }

  @Override
  public int delete(int no) throws Exception {
    return articleDao.delete(no);
  }
}
