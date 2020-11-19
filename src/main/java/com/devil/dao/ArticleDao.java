package com.devil.dao;

import java.util.List;
import com.devil.domain.Article;

public interface ArticleDao {
  public int insert(Article article) throws Exception;

  public List<Article> findAll(String keyword);
}
