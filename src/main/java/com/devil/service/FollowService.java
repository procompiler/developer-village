package com.devil.service;

import java.util.Map;
import com.devil.domain.Follow;

public interface FollowService {
  int add(Follow follow) throws Exception;
  Follow get(Map<String, Object> map) throws Exception;
  int delete(Follow follow) throws Exception;
}