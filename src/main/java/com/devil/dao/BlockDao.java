package com.devil.dao;

import java.util.List;
import com.devil.domain.Block;

public interface BlockDao {
  int insertArticleReport(Block block) throws Exception;
  int insertCommentReport(Block block) throws Exception;
  List<Block> findAll(String keyword) throws Exception;
  Block findByNo(int no) throws Exception;
  Block findByUser(int userNo) throws Exception;
}
