package com.devil.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.devil.dao.BadgeStanDao;
import com.devil.domain.BadgeStan;
import com.devil.service.BadgeStanService;

@Service
public class DefaultBadgeStanService implements BadgeStanService {
  BadgeStanDao badgeStanDao;

  public DefaultBadgeStanService(BadgeStanDao badgeStanDao) {
    this.badgeStanDao = badgeStanDao;
  }

  @Override
  public int add(BadgeStan badgeStan) throws Exception {
    return badgeStanDao.insert(badgeStan);
  }

  @Override
  public int update(BadgeStan badgeStan) throws Exception {
    return badgeStanDao.update(badgeStan);
  }

  @Override
  public List<BadgeStan> list(int badgeNo) throws Exception {
    return badgeStanDao.findByBadgeNo(badgeNo);
  }
}
