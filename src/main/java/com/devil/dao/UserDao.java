package com.devil.dao;

import java.util.List;
import com.devil.domain.User;

public interface UserDao {

  public int insert(User user) throws Exception;
  public int delete(int no) throws Exception;
  public User findByNo(int no) throws Exception;
  public List<User> findByName(String name) throws Exception;
  public List<User> findAll(String keyword) throws Exception;
  public int update(User user) throws Exception;
  public User findByEmailPassword(String email, String password) throws Exception;
  public int inactive(int no) throws Exception;

}
