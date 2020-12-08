package com.devil.service;

import java.util.Map;
import com.devil.dao.FollowDao;
import com.devil.domain.Follow;

public class DefaultFollowService implements FollowService {
  FollowDao followDao;

  public DefaultFollowService(FollowDao followDao) {
    this.followDao = followDao;
  }

  @Override
  public int add(Follow follow) throws Exception {
    return followDao.insert(follow);
  }

  @Override
  public Follow get(Map<String, Object> map) throws Exception {
    return followDao.findByUserFollowee(map);
  }

  @Override
  public int delete(Follow follow) throws Exception {
    return followDao.delete(follow);
  }
}
