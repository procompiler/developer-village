package com.devil.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.devil.dao.BadgeStanDao;
import com.devil.domain.BadgeStan;
import com.devil.domain.User;
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
  public List<BadgeStan> list(String keyword) throws Exception {
    return badgeStanDao.findAll(keyword);
  }

  @Override
  public BadgeStan get(int no) throws Exception {
    BadgeStan badgeStan = badgeStanDao.findByNo(no);
    return badgeStan;
  }

  @Override
  public int update(BadgeStan badgeStan) throws Exception {
    return badgeStanDao.update(badgeStan);
  }

  @Override
  public int delete(int no) throws Exception {
    return badgeStanDao.inactive(no);
  }

  @Override
  public List<BadgeStan> list(User user) throws Exception {
    return badgeStanDao.findByCollector(user);
  }
}
