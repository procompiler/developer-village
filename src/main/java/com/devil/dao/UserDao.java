package com.devil.dao;

import java.util.List;
import com.devil.domain.Block;
import com.devil.domain.User;

public interface UserDao {
  int insert(User user) throws Exception;
  int delete(int no) throws Exception;
  User findByNo(int no) throws Exception;
  List<User> findByName(String name) throws Exception;
  List<User> findAll(String keyword) throws Exception;
  int update(User user) throws Exception;
  User findByEmailPassword(String email, String password) throws Exception;
  int inactive(int no) throws Exception;
  List<User> findByFollower(User user) throws Exception;
  int insertBlocked(Block block) throws Exception;
}
