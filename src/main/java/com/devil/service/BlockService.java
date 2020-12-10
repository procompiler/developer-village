package com.devil.service;

import java.util.List;
import com.devil.domain.Block;
import com.devil.domain.User;

public interface BlockService {
  public int block(Block block) throws Exception;
  public List<Block> list (String keyword) throws Exception;
  public Block get(User user) throws Exception;
  public int update(Block block) throws Exception;

}
