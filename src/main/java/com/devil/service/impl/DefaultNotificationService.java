package com.devil.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.devil.dao.NotificationDao;
import com.devil.domain.Notification;
import com.devil.domain.User;
import com.devil.service.NotificationService;

@Service
public class DefaultNotificationService implements NotificationService {
  NotificationDao notificationDao;

  public DefaultNotificationService(NotificationDao notificationDao) {
    this.notificationDao = notificationDao;
  }

@Override
public List<Notification> list(User user) throws Exception {
  // TODO Auto-generated method stub
  return null;
}
@Override
public int add(Notification notification) throws Exception {
  // TODO Auto-generated method stub
  return 0;
}
}
