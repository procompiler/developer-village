package com.devil.service;

import java.util.List;
import com.devil.domain.Badge;

public interface BadgeService {
  int add(Badge badge) throws Exception;
  List<Badge> list (int no) throws Exception;
  //Article get(int no) throws Exception;
}
