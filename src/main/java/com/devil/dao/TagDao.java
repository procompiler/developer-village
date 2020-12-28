package com.devil.dao;

import java.util.List;
import com.devil.domain.Tag;
import com.devil.domain.User;

public interface TagDao {
  List<Tag> findAll(String keyword) throws Exception;
  List<Tag> findByFollower(User user) throws Exception;
  int insert(Tag tag) throws Exception;
  int update(Tag tag) throws Exception;
  int delete(int no) throws Exception;
  int inactive(int no) throws Exception;
  int active(int no) throws Exception;
  Tag findByNo(int no) throws Exception;
}
