package com.devil.dao;

import java.util.Map;
import com.devil.domain.Follow;

public interface FollowDao {
  int insertTag(Follow follow) throws Exception;
  int deleteTag(Follow follow) throws Exception;
  int insertUser(Follow follow) throws Exception;
  int deleteUser(Follow follow) throws Exception;
  Follow findByUserTag(Map<String, Object> map) throws Exception;
  Follow findByUserUser(Map<String, Object> map) throws Exception;
}
