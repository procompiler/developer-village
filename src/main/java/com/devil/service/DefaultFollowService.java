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
  public int addTag(Follow follow) throws Exception {
    return followDao.insertTag(follow);
  }

  @Override
  public int deleteTag(Follow follow) throws Exception {
    return followDao.deleteTag(follow);
  }

  @Override
  public Follow getTag(Map<String, Object> map) throws Exception {
    return followDao.findByUserTag(map);
  }

  @Override
  public int addUser(Follow follow) throws Exception {
    return followDao.insertUser(follow);
  }

  @Override
  public int deleteUser(Follow follow) throws Exception {
    return followDao.deleteUser(follow);
  }

  @Override
  public Follow getUser(Map<String, Object> map) throws Exception {
    return followDao.findByUserUser(map);
  }

}
