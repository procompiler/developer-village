package com.devil.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.devil.dao.FollowDao;
import com.devil.dao.NotificationDao;
import com.devil.dao.UserDao;
import com.devil.domain.Follow;
import com.devil.domain.Notification;
import com.devil.domain.User;
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
    notificationDao.insert(makeNoti(follow.getFollower(), follow.getFolloweeNo()));
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
  public Notification makeNoti(User user, int userNo) {
    return new Notification()
        .setUserNo(userNo)
        .setContent(makeNotiContent(user))
        .setUrl(makeNotiUrl(user))
        .setPhoto(user.getPhoto())
        .setType(3);
  }

  // 알림 컨텐츠 만들기
  public String makeNotiContent(User user) {
    return user.getNickname() + "님이 팔로우했습니다.";
  }

  // 알림 주소 만들기
  public String makeNotiUrl(User user) {
    return "/user/" + user.getNo();
  }
}
