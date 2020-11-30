package com.devil.service;

import java.util.List;
import com.devil.domain.Badge;

public interface BadgeService {
  int add(Badge badge) throws Exception;
  List<Badge> list (String keyword) throws Exception;
  Badge get(int no) throws Exception;
  int update(Badge badge) throws Exception;
  int delete(int no) throws Exception;
  
}
