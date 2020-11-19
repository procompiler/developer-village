package com.devil.service;

import java.util.List;
import com.devil.dao.ArticleDao;
import com.devil.domain.Article;
import com.devil.util.SqlSessionFactoryProxy;

public class DefaultArticleService implements ArticleService {
  ArticleDao articleDao;
  SqlSessionFactoryProxy factoryProxy;
  
  public DefaultArticleService(ArticleDao articleDao, SqlSessionFactoryProxy factoryProxy) {
    this.articleDao = articleDao;
    this.factoryProxy = factoryProxy;
  }
  
  @Override
  public int add(Article article) throws Exception {
    return articleDao.insert(article);
  }

  @Override
  public List<Article> list(String keyword) throws Exception {
    return articleDao.findAll(keyword);
  }
  
}
