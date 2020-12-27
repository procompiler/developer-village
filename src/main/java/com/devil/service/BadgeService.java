package com.devil.service;

import java.util.List;
import com.devil.domain.Badge;
import com.devil.domain.BadgeStan;
import com.devil.domain.User;

public interface BadgeService {
  int add(Badge badge) throws Exception;
  List<Badge> list (String keyword) throws Exception;
  Badge get(int no) throws Exception;
  int update(Badge badge) throws Exception;
  int delete(int no) throws Exception;
  //List<Badge> list(Map<String,Object> keywords) throws Exception;
  //List<Badge> list() throws Exception;
  List<Badge> list (User user) throws Exception;
 List<BadgeStan> badgeStans(int bno) throws Exception;
}
