package com.devil.dao;

import java.util.List;
import java.util.Map;
import com.devil.domain.Article;
import com.devil.domain.User;

public interface ArticleDao {
  int insert(Article article) throws Exception;
  int update(Article article) throws Exception;
  int delete(int no) throws Exception;
  Article findByNo(int no) throws Exception;
  int updateViewCount(int no) throws Exception;
  List<Article> findByDetailKeyword(Map<String, Object> keywords);
  List<Article> findByCategoryNo(int categoryNo);
  List<Article> findByCategoryNoKeyword(Map<String, Object> map);
  int inactivate(int no) throws Exception;
  int activate(int no) throws Exception;
  List<Article> findAll(String keyword) throws Exception;
  List<Article> findAllAdmin(Map<String, Object> map) throws Exception;
  List<Article> findByBookmarker(User user) throws Exception;
  List<Article> findByWriter(User user);
  List<Article> findByTagNo(int tagNo) throws Exception;
  List<Article> findByTagNoKeyword(Map<String, Object> map) throws Exception;
  int insertTags(Article article) throws Exception;
  int deleteTags(int articleNo) throws Exception;
  List<Article> findFeedByUser(User user) throws Exception;
  int count(String keyword) throws Exception;
}
