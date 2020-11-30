package com.devil.service;

import java.util.List;

import com.devil.dao.BadgeDao;
import com.devil.domain.Badge;

public class DefaultBadgeService implements BadgeService {
  BadgeDao badgeDao;

  public DefaultBadgeService(BadgeDao badgeDao) {
    this.badgeDao = badgeDao;
  }

  @Override
  public int add(Badge badge) throws Exception {
    return badgeDao.insert(badge);
  }

  @Override
  public List<Badge> list(String keyword) throws Exception {
    return badgeDao.findAll(keyword);
  }

  @Override
  public Badge get(int no) throws Exception {
    Badge badge = badgeDao.findByNo(no);
    return badge;
  }

@Override
public int update(Badge badge) throws Exception {
	return badgeDao.update(badge);
}

@Override
public int delete(int no) throws Exception {
	return badgeDao.delete(no);
}

}
