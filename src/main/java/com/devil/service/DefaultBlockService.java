package com.devil.service;

import java.util.List;
import com.devil.dao.BlockDao;
import com.devil.dao.ReportDao;
import com.devil.dao.UserDao;
import com.devil.domain.Block;
import com.devil.domain.User;
import com.devil.util.SqlSessionFactoryProxy;

public class DefaultBlockService implements BlockService {
  BlockDao blockDao;
  UserDao userDao;
  ReportDao reportDao;
  SqlSessionFactoryProxy factoryProxy;

  public DefaultBlockService(BlockDao blockDao, UserDao userDao,
      ReportDao reportDao, SqlSessionFactoryProxy factoryProxy) {
    this.blockDao = blockDao;
    this.userDao = userDao;
    this.reportDao = reportDao;
    this.factoryProxy = factoryProxy;
  }

  @Override
  public int block(Block block) throws Exception {
    try {
      factoryProxy.startTransaction();
      blockDao.insert(block);
      userDao.insertBlocked(block);
      reportDao.insertStatus(block);
      factoryProxy.commit();
      return 1;
    } catch (Exception e) {
      throw e;
    } finally {
      factoryProxy.endTransaction();
    }
  }

  @Override
  public List<Block> list(String keyword) throws Exception {
    return blockDao.findAll(keyword);
  }

  @Override
  public Block get(User user) throws Exception {
    return blockDao.findByUser(user);
  }

  @Override
  public int update(Block block) throws Exception {
    return 0;
  }

}
