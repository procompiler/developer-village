package com.devil.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.devil.dao.BlockDao;
import com.devil.dao.ReportDao;
import com.devil.dao.UserDao;
import com.devil.domain.Block;

@Service
public class DefaultBlockService implements BlockService {
  BlockDao blockDao;
  UserDao userDao;
  ReportDao reportDao;
//  SqlSessionFactoryProxy factoryProxy;

  public DefaultBlockService(BlockDao blockDao, UserDao userDao,
      ReportDao reportDao/*, SqlSessionFactoryProxy factoryProxy*/) {
    this.blockDao = blockDao;
    this.userDao = userDao;
    this.reportDao = reportDao;
//    this.factoryProxy = factoryProxy;
  }

  @Override
  public int block(Block block) throws Exception {
    try {
//      factoryProxy.startTransaction();
      try {
        blockDao.insertArticleReport(block);
      } catch(Exception e) {
        blockDao.insertCommentReport(block);
      }

      userDao.insertBlocked(block);
      reportDao.insertStatus(block);
//      factoryProxy.commit();
      return 1;
    } catch (Exception e) {
      throw e;
    } finally {
//      factoryProxy.endTransaction();
    }
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
  public int update(Block block) throws Exception {
    return 0;
  }

}
