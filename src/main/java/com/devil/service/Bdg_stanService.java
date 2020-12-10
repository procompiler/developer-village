package com.devil.service;

import java.util.List;
import com.devil.domain.Badge;
import com.devil.domain.User;

public interface Bdg_stanService {
  int add(Badge badge) throws Exception;
  List<Badge> list (User user) throws Exception;
  Badge get(int no) throws Exception;
  int update(Badge badge) throws Exception;
  int delete(int no) throws Exception;
  //List<Badge> list(Map<String,Object> keywords) throws Exception;
  //List<Badge> list() throws Exception;
}
