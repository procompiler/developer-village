package com.devil.service;

import java.util.List;
import java.util.Map;
import com.devil.domain.Article;

public interface ArticleService {
  int add(Article article) throws Exception;
  List<Article> list (String keyword) throws Exception;
  Article get(int no) throws Exception;
  int update(Article article) throws Exception;
  int delete(int no) throws Exception;
  List<Article> list () throws Exception;
  List<Article> list(Map<String, Object> keywords) throws Exception;
  List<Article> list(int categoryNo) throws Exception;
}
