package com.devil.service;

import java.util.List;
import java.util.Map;
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
    try {
      factoryProxy.startTransaction();
      articleDao.insert(article);
      articleDao.insertTags(article);
      factoryProxy.commit();
      return 1;
    } catch (Exception e) {
      factoryProxy.rollback();
      throw e;
    } finally {
      factoryProxy.endTransaction();
    }
  }
  @Override
  public List<Article> list(Map<String, Object> keywords) {
    return articleDao.findByDetailKeyword(keywords);
  }

  @Override
  public List<Article> list(String keyword) throws Exception {
    return articleDao.findAll(keyword);
  }
  @Override
  public List<Article> list() throws Exception {
    return articleDao.findAll((String)null);
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
