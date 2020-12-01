package com.devil.service;

import java.util.List;
import com.devil.dao.UserDao;
import com.devil.domain.User;

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
  public User get(int no) throws Exception {
    return userDao.findByNo(no);
  }

  @Override
  public User get(String email, String password) throws Exception {
    return userDao.findByEmailPassword(email, password);
  }
  @Override
  public int update(User user) throws Exception {
    return userDao.update(user);
  }

  @Override
  public int delete(int no) throws Exception {
    return userDao.inactive(no);
  }
}
