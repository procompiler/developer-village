package com.devil.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.devil.dao.BlockDao;
import com.devil.dao.ReportDao;
import com.devil.dao.UserDao;
import com.devil.domain.Block;
import com.devil.service.BlockService;

@Service
public class DefaultBlockService implements BlockService {
  BlockDao blockDao;
  UserDao userDao;
  ReportDao reportDao;

  public DefaultBlockService(BlockDao blockDao, UserDao userDao,
      ReportDao reportDao) {
    this.blockDao = blockDao;
    this.userDao = userDao;
    this.reportDao = reportDao;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public int block(Block block) throws Exception {
    blockDao.insertArticleReport(block);
    blockDao.insertCommentReport(block);

    userDao.insertBlocked(block);
    reportDao.insertStatus(block);
    return 1;
  }

  @Override
  public List<Block> list(String keyword) throws Exception {
    return blockDao.findAll(keyword);
  }

  @Override
  public Block get(int no) throws Exception {
    return blockDao.findByNo(no);
  }

  @Override
  public Block getBlockedUser(int blockedUser) throws Exception {
    return blockDao.findByUser(blockedUser);
  }

  @Override
  public int update(Block block) throws Exception {
    return 0;
  }

}
