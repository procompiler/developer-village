package com.devil.dao;

import java.util.List;
import com.devil.domain.Block;

public interface BlockDao {
  int insertArticleReport(Block block) throws Exception;
  int insertCommentReport(Block block) throws Exception;
  int update(Block block) throws Exception; // Mapper에 구현 안함
  List<Block> findAll(String keyword) throws Exception;
  Block findByNo(int no) throws Exception;
  Block findByUser(int userNo) throws Exception;
}
