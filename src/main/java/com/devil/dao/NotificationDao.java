package com.devil.dao;

import java.util.List;
import com.devil.domain.Tag;

public interface NotificationDao {
  int insert(Tag tag) throws Exception;
  List<Tag> findByUser(String keyword) throws Exception;
}
