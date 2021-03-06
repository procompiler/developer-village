package com.devil.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public int add(User user) throws Exception {
    return userDao.insert(user);
  }

  @Override
  public List<User> list(String keyword) throws Exception {
    return userDao.findAll(keyword);
  }

  @Override
  public List<User> adminList(String keyword, int pageNo, int pageSize) throws Exception {
    Map<String,Object> map = new HashMap<>();
    if (keyword != null) {
      map.put("keyword", keyword);
    }
    map.put("startRowNo", (pageNo - 1) * pageSize);
    map.put("pageSize", pageSize);

    return userDao.findAllAdmin(map);
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
  public User get(String email) throws Exception {
    return userDao.findByEmail(email);
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
  public int updatePwd(int userNo, String password) throws Exception {
    Map<String,Object> map = new HashMap<>();
    map.put("no", userNo);
    map.put("password", password);
    return userDao.updatePwd(map);
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
  public int undelete(int no) throws Exception {
    return userDao.active(no);
  }

  @Override
  public List<User> listFollowing(User user) throws Exception {
    return userDao.findByFollower(user);
  }

  @Override
  public List<User> listFollower(User user) throws Exception {
    return userDao.findFollower(user);
  }

  @Override
  public int size(String keyword) throws Exception {
    return userDao.count(keyword).size();
  }
}
