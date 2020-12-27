package com.devil.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.devil.dao.UserDao;
import com.devil.domain.User;
import com.devil.service.UserService;

@Service
public class DefaultUserService implements UserService {
  UserDao userDao;

  public DefaultUserService(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public int add(User user) throws Exception {
    return userDao.insert(user);
  }

  @Override
  public List<User> list(String keyword) throws Exception {
    return userDao.findAll(keyword);
  }

  @Override
  public User get(Map<String, Object> params) throws Exception {
    return userDao.findByNo(params);
  }

  @Override
  public User get(String email, String password) throws Exception {
    Map<String,Object> map = new HashMap<>();
    map.put("email", email);
    map.put("password", password);
    return userDao.findByEmailPassword(map);
  }

  @Override
  public User getId(String name, String tel) throws Exception {
    Map<String,Object> map = new HashMap<>();
    map.put("name", name);
    map.put("tel", tel);
    return userDao.findId(map);
  }

  @Override
  public User getPwd(String email, String name, String tel) throws Exception {
    Map<String,Object> map = new HashMap<>();
    map.put("email", email);
    map.put("name", name);
    map.put("tel", tel);
    return userDao.findPassword(map);
  }

  @Override
  public int update(User user) throws Exception {
    return userDao.update(user);
  }

  @Override
  public int updateLoginTimeStamp(User user) throws Exception {
    return userDao.updateLoginTimeStamp(user);
  }

  @Override
  public int delete(int no) throws Exception {
    return userDao.inactive(no);
  }

  @Override
  public List<User> listFollowing(User user) throws Exception {
    return userDao.findByFollower(user);
  }

  @Override
  public List<User> listFollower(User user) throws Exception {
    return userDao.findFollower(user);
  }
}
