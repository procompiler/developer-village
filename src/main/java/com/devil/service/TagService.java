package com.devil.service;

import java.util.List;
import com.devil.domain.Tag;
import com.devil.domain.User;

public interface TagService {
  List<Tag> list(String keyword) throws Exception;
  List<Tag> listByFollower(User user) throws Exception;
  int add(Tag tag) throws Exception;
  int update(Tag tag) throws Exception;
  int delete(int no) throws Exception;
  int undelete(int no) throws Exception;
  Tag get(int no) throws Exception;
}