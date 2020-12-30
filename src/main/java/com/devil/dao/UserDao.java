package com.devil.dao;

import java.util.List;
import java.util.Map;
import com.devil.domain.Block;
import com.devil.domain.User;

public interface UserDao {
  List<User> findByName(String name) throws Exception;
  List<User> findAll(String keyword) throws Exception;
  List<User> findByFollower(User user) throws Exception;
  List<User> findFollower(User user) throws Exception;
  int insert(User user) throws Exception;
  int update(User user) throws Exception;
  int updatePwd(Map<String, Object> map) throws Exception;
  int delete(int no) throws Exception;
  int insertBlocked(Block block) throws Exception;
  int updateLoginTimeStamp(User user) throws Exception;
  int inactive(int no) throws Exception;
  int active(int no) throws Exception;
  User findByNo(Map<String, Object> params) throws Exception;
  User findByEmailPassword(Map<String, Object> map) throws Exception;
  User findId(Map<String, Object> map) throws Exception;
  User findPassword(Map<String, Object> map) throws Exception;
  User findByEmail(String email) throws Exception;
}
