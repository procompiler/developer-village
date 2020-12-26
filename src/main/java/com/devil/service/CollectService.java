package com.devil.service;

import java.util.Map;
import com.devil.domain.Follow;

public interface CollectService {
  int addTag(Follow follow) throws Exception;
  int deleteTag(Follow follow) throws Exception;
  Follow getTag(Map<String, Object> map) throws Exception;
  Follow getUser(Map<String, Object> map) throws Exception;
  int addUser(Follow follow) throws Exception;
  int deleteUser(Follow follow) throws Exception;
}