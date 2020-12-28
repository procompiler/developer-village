package com.devil.dao;

import java.util.List;
import com.devil.domain.Notification;
import com.devil.domain.User;

public interface NotificationDao {
  int insert(Notification notification) throws Exception;
  List<Notification> findByUser(User user) throws Exception;
  int updateReadDate(int no) throws Exception;
}
