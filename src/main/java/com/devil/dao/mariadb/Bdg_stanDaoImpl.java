package com.devil.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.devil.dao.Bdg_stanDao;
import com.devil.domain.Bdg_stan;

public class Bdg_stanDaoImpl implements Bdg_stanDao {

  SqlSessionFactory sqlSessionFactory;

  public Bdg_stanDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Bdg_stan bdg_stan) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.insert("Bdg_stanDao.insert", bdg_stan);
    }
  }

  @Override
  public Bdg_stan findByNo(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("Bdg_stanDao.findByNo", no);
    }
  }

  @Override
  public List<Bdg_stan> findAll(String keyword) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("Bdg_stanDao.findAll", keyword);
    }
  }


  @Override
  public int update(Bdg_stan bdg_stan) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("Bdg_stanDao.update", bdg_stan);
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("Bdg_stanDao.delete", no);
    }
  }

}
