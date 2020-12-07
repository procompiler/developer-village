package com.devil.dao;

import java.util.Map;
import com.devil.domain.Bookmark;

public interface BookmarkDao {
  int insert(Bookmark bookmark) throws Exception;
  int delete(Bookmark bookmark) throws Exception;
  Bookmark findByUserArticle(Map<String, Object> map) throws Exception;
}
