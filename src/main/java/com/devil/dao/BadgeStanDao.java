package com.devil.dao;

import java.util.List;
import com.devil.domain.BadgeStan;

public interface BadgeStanDao {
  public int insert(BadgeStan badgeStan) throws Exception;
  public int update(BadgeStan badgeStan) throws Exception;
  public List<BadgeStan> findByBadgeNo(int badgeNo) throws Exception;
}
