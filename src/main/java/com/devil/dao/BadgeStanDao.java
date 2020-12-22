package com.devil.dao;

import java.util.List;
import com.devil.domain.BadgeStan;
import com.devil.domain.User;

public interface BadgeStanDao {
  public int insert(BadgeStan badgeStan) throws Exception;
  public int update(BadgeStan badgeStan) throws Exception;
  public List<BadgeStan> findAll(String keyword) throws Exception;
  public BadgeStan findByNo(int no) throws Exception;
  public int delete(int no) throws Exception;
  public int inactive(int no) throws Exception;
  public List<BadgeStan> findByCollector(User user) throws Exception;
}
