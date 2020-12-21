package com.devil.service;

import java.util.List;
import java.util.Map;
import com.devil.domain.User;

public interface UserService {
  List<User> list (String keyword) throws Exception;
  User get(Map<String, Object> params) throws Exception;
  User get(String email, String password) throws Exception;
  int add(User user) throws Exception;
  int update(User user) throws Exception;
  int delete(int no) throws Exception;
  List<User> listFollowing(User user) throws Exception;
  List<User> listFollower(User user) throws Exception;
  int updateLoginTimeStamp(User user) throws Exception;
}
