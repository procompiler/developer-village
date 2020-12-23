package com.devil.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.devil.dao.FollowDao;
import com.devil.dao.NotificationDao;
import com.devil.dao.UserDao;
import com.devil.domain.Article;
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

  @Override
  public int addUser(Follow follow) throws Exception {
    int count = followDao.insertUser(follow);

    /*
    // 알림 만들기
    getNotiInfo(follow)
    notificationDao.insert(makeNoti());
    */
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

  /*
  public User getNotiInfo(Follow follow) throws Exception {
    Map<String, Object> params = new HashMap<>();
    params.put("type", "app");
    params.put("no", follow.getUserNo());
    return userDao.findByNo(params);
  }
  
  public Notification getNoti(User user, int ) {
    Notification noti = new Notification();
    noti.setContent(makeNotiContent(user));
    noti.setUrl(makeNotiUrl(user));
    noti.setType(1);
    noti.setUserNo();
    return noti;    
  }

  public String makeNotiContent(User user) {
    return user.getNickname() + "님께서 팔로우하셨습니다!";
  }

  public String makeNotiUrl(User user) {
    return "/user/detail?no=" + user.getNo();
  }
  */
}
