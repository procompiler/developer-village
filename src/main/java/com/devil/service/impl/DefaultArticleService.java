package com.devil.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.devil.dao.ArticleDao;
import com.devil.domain.Article;
import com.devil.domain.User;
import com.devil.service.ArticleService;

@Service
public class DefaultArticleService implements ArticleService {
  ArticleDao articleDao;

  public DefaultArticleService(ArticleDao articleDao) {
    this.articleDao = articleDao;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public int add(Article article) throws Exception {
    articleDao.insert(article);
    articleDao.insertTags(article);
    return 1;
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
  public List<Article> listByCategoryNoKeyword(Map<String, Object> map) throws Exception {
    return articleDao.findByCategoryNoKeyword(map);
  }

  @Override
  public List<Article> list() throws Exception {
    return articleDao.findAll((String)null);
  }

  @Override
  public List<Article> adminList() throws Exception {
    return articleDao.findAllAdmin((String)null);
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
    return articleDao.inactivate(no);
  }

  @Override
  public int undelete(int no) throws Exception {
    return articleDao.activate(no);
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

  @Override
  public List<Article> listByTagNoKeyword(Map<String, Object> map) throws Exception {
    return articleDao.findByTagNoKeyword(map);
  }

  @Override
  public List<Article> feedList(User user) throws Exception {
    return articleDao.findFeedByUser(user);
  }
}
