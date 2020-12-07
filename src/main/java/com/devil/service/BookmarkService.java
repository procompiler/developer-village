package com.devil.service;

import java.util.Map;
import com.devil.domain.Bookmark;

public interface BookmarkService {
  int add(Bookmark bookmark) throws Exception;
  Bookmark get(Map<String, Object> map) throws Exception;
  int delete(Bookmark bookmark) throws Exception;
}