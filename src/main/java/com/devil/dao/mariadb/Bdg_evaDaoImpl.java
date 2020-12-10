package com.devil.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.devil.dao.Bdg_evaDao;
import com.devil.domain.Bdg_eva;

public class Bdg_evaDaoImpl implements Bdg_evaDao {

  SqlSessionFactory sqlSessionFactory;

  public Bdg_evaDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Bdg_eva bdg_eva) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.insert("Bdg_evaDao.insert", bdg_eva);
    }
  }

  @Override
  public Bdg_eva findByNo(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("Bdg_evaDao.findByNo", no);
    }
  }

  @Override
  public List<Bdg_eva> findAll(String keyword) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("Bdg_evaDao.findAll", keyword);
    }
  }


  @Override
  public int update(Bdg_eva bdg_eva) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("Bdg_evaDao.update", bdg_eva);
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("Bdg_evaDao.delete", no);
    }
  }

}
