package com.devil.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.devil.dao.BlockDao;
import com.devil.domain.Block;

public class BlockDaoImpl implements BlockDao {

  SqlSessionFactory sqlSessionFactory;

  public BlockDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Block block) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.insert("BlockDao.insert", block);
    }
  }

  @Override
  public int update(Block block) throws Exception {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public List<Block> findAll(String keyword) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("BlockDao.findAll", keyword);
    }
  }

  @Override
  public Block findByNo(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("BlockDao.findByNo", no);
    }
  }

}
