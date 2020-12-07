package com.devil.dao;

import java.util.List;
import com.devil.domain.Block;

public interface BlockDao {
  int insert(Block block) throws Exception;
  int update(Block block) throws Exception;
  List<Block> findAll(String keyword) throws Exception;
  Block findByNo(int no) throws Exception;
}
