package com.devil.service;

import java.util.List;
import com.devil.domain.BadgeStan;
import com.devil.domain.User;

public interface BadgeStanService {
  int add(BadgeStan badgeStan) throws Exception;
  List<BadgeStan> list (String keyword) throws Exception;
  BadgeStan get(int no) throws Exception;
  int update(BadgeStan badgeStan) throws Exception;
  int delete(int no) throws Exception;
  //List<Badge> list(Map<String,Object> keywords) throws Exception;
  //List<Badge> list() throws Exception;
  List<BadgeStan> list (User user) throws Exception;
}
