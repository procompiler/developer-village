package com.devil.service.impl;

import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.devil.dao.CollectDao;
import com.devil.dao.NotificationDao;
import com.devil.domain.Collect;
import com.devil.domain.Notification;
import com.devil.service.CollectService;

@Service
public class DefaultCollectService implements CollectService {
  CollectDao collectDao;
  NotificationDao notificationDao;

  public DefaultCollectService(CollectDao collectDao, NotificationDao notificationDao) {
    this.collectDao = collectDao;
    this.notificationDao = notificationDao;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public int add(Collect collect) throws Exception {
    int count = collectDao.insert(collect);
    notificationDao.insert(makeNoti(collect));
    return count;
  }

  @Override
  public int updateOrder(Collect collect) throws Exception {
    return collectDao.updateOrder(collect);
  }
  
  @Override
  public int delete(Collect collect) throws Exception {
    return collectDao.delete(collect);
  }
  
  @Override
  public int getCount(Map<String, Object> params) throws Exception {
    return collectDao.findCountByUser(params);
  }
  
  // 알림 만들기
  public Notification makeNoti(Collect collect) {
    return new Notification()
    .setUserNo(collect.getUser().getNo())
    .setBadge(collect.getBadge())
    .setType(4);
  }
}
