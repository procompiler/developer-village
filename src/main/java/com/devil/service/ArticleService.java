package com.devil.service;

import java.util.List;
import java.util.Map;
import com.devil.domain.Article;
import com.devil.domain.User;

public interface ArticleService {
  int add(Article article) throws Exception;
  Article get(int no) throws Exception;
  int update(Article article) throws Exception;
  int delete(int no) throws Exception;
  int undelete(int no) throws Exception;
  List<Article> list() throws Exception;
  List<Article> list(String keyword) throws Exception;
  List<Article> list(Map<String, Object> keywords) throws Exception;
  List<Article> list(int categoryNo) throws Exception;
  List<Article> list(User user) throws Exception;
  List<Article> bookmarkList(User user) throws Exception;
  List<Article> listByTagNo(int tagNo) throws Exception;
  List<Article> feedList(User user) throws Exception;
}
