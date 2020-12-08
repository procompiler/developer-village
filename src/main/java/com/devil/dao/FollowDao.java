package com.devil.dao;

import java.util.Map;
import com.devil.domain.Follow;

public interface FollowDao {
  int insert(Follow follow) throws Exception;
  int delete(Follow follow) throws Exception;
  Follow findByUserFollow(Map<String, Object> map) throws Exception;
}
