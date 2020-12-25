package com.devil.service;

import java.util.List;
import com.devil.domain.Block;

public interface BlockService {
  public int block(Block block) throws Exception;
  public List<Block> list (String keyword) throws Exception;
  public Block get(int no) throws Exception;
  public Block getByUser(int blockedUser) throws Exception;
  public int update(Block block) throws Exception;

}
