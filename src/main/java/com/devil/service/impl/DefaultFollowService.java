package com.devil.service.impl;

import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.devil.dao.FollowDao;
import com.devil.dao.NotificationDao;
import com.devil.dao.UserDao;
import com.devil.domain.Follow;
import com.devil.domain.Notification;
import com.devil.service.FollowService;

@Service
public class DefaultFollowService implements FollowService {
  FollowDao followDao;
  UserDao userDao;
  NotificationDao notificationDao;

  public DefaultFollowService(FollowDao followDao, UserDao userDao, NotificationDao notificationDao) {
    this.followDao = followDao;
    this.userDao = userDao;
    this.notificationDao = notificationDao;
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

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  @Override
  public int addUser(Follow follow) throws Exception {
    int count = followDao.insertUser(follow);
    // 알림 insert
    notificationDao.insert(makeNoti(follow));
    return count;
  }

  @Override
  public int deleteUser(Follow follow) throws Exception {
    return followDao.deleteUser(follow);
  }

  @Override
  public Follow getUser(Map<String, Object> map) throws Exception {
    return followDao.findByUserUser(map);
  }
  
  // 알림 만들기
  public Notification makeNoti(Follow follow) {
    return new Notification()
    .setUserNo(follow.getFolloweeNo())
    .setFollower(follow.getFollower())
    .setType(3);
  }
}
