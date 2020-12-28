package com.devil.service;

import java.util.List;
import com.devil.domain.Notification;
import com.devil.domain.User;

public interface NotificationService {
  List<Notification> list(User user) throws Exception;

  int updateReadDate(int no) throws Exception;
  int size(User user) throws Exception;
}