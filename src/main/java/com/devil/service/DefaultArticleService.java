package com.devil.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.devil.dao.ArticleDao;
import com.devil.domain.Article;
import com.devil.domain.User;

@Service
public class DefaultArticleService implements ArticleService {
  ArticleDao articleDao;
//  SqlSessionFactoryProxy factoryProxy;

  public DefaultArticleService(ArticleDao articleDao/*, SqlSessionFactoryProxy factoryProxy*/) {
    this.articleDao = articleDao;
//    this.factoryProxy = factoryProxy;
  }

  @Override
  public int add(Article article) throws Exception {
    try {
//      factoryProxy.startTransaction();
      articleDao.insert(article);
      articleDao.insertTags(article);
//      factoryProxy.commit();
      return 1;
    } catch (Exception e) {
//      factoryProxy.rollback();
      throw e;
    } finally {
//      factoryProxy.endTransaction();
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
  public List<Article> list(int categoryNo) throws Exception {
    return articleDao.findByCategoryNo(categoryNo);
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
    articleDao.deleteTags(article.getNo());
    articleDao.insertTags(article);

    return articleDao.update(article);
  }

  @Override
  public int delete(int no) throws Exception {
    return articleDao.inactive(no);
  }

  @Override
  public List<Article> bookmarkList(User user) throws Exception {
    return articleDao.findByBookmarker(user);
  }

  @Override
  public List<Article> list(User user) throws Exception {
    return articleDao.findByWriter(user);
  }

  @Override
  public List<Article> listByTagNo(int tagNo) throws Exception {
    return articleDao.findByTagNo(tagNo);
  }

}
