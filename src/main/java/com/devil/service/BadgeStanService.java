package com.devil.service;

import java.util.List;
import com.devil.domain.Badge;
import com.devil.domain.BadgeStan;

public interface BadgeStanService {
  int add(BadgeStan badgeStan) throws Exception;
  List<BadgeStan> list(int badgeNo) throws Exception;
  int update(BadgeStan badgeStan) throws Exception;
  int delete(int no) throws Exception;
}
