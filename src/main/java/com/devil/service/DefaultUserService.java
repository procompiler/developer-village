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
  public List<User> list(String keyword) throws Exception {
    return userDao.findAll(keyword);
  }
}
