package com.devil.dao;

import java.util.List;
import com.devil.domain.Notification;
import com.devil.domain.Tag;
import com.devil.domain.User;

public interface NotificationDao {
  int insert(Tag tag) throws Exception;
  List<Notification> findByUser(User user) throws Exception;
}
