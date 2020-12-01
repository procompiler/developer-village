package com.devil.dao;

import java.util.List;
import com.devil.domain.Article;

public interface ArticleDao {
  int insert(Article article) throws Exception;
  int update(Article article) throws Exception;
  List<Article> findAll(String keyword) throws Exception;
  int delete(int no) throws Exception;
  Article findByNo(int no) throws Exception;
  int updateViewCount(int no) throws Exception;
  int insertTags(Article article) throws Exception;
}
