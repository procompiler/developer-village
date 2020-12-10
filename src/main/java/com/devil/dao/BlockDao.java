package com.devil.dao;

import java.util.List;
import com.devil.domain.Block;
import com.devil.domain.User;

public interface BlockDao {
  int insert(Block block) throws Exception;
  int update(Block block) throws Exception;
  List<Block> findAll(String keyword) throws Exception;
  Block findByUser(User user) throws Exception;
}
