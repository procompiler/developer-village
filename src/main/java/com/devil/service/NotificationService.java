package com.devil.service;

import java.util.List;
import com.devil.domain.Notification;
import com.devil.domain.User;

public interface NotificationService {
  int add(Notification notification) throws Exception;
  List<Notification> list(User user) throws Exception;
}