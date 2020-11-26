package com.devil.dao;

import java.util.List;
import com.devil.domain.Article;

public interface ArticleDao {
  public int insert(Article article) throws Exception;
  public int update(Article article) throws Exception;
  public List<Article> findAll(String keyword) throws Exception;
  public int delete(int no) throws Exception;
  public Article findByNo(int no) throws Exception;
  public int updateViewCount(int no) throws Exception;
}
