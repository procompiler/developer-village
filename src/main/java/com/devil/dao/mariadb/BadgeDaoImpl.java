package com.devil.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.devil.dao.BadgeDao;
import com.devil.domain.Badge;

public class BadgeDaoImpl implements BadgeDao {

  SqlSessionFactory sqlSessionFactory;

  public BadgeDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Badge badge) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.insert("BadgeDao.insert", badge);
    }
  }

  @Override
  public Badge findByNo(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("BadgeDao.findByNo", no);
    }
  }

  @Override
  public List<Badge> findAll(String keyword) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("BadgeDao.findAll", keyword);
    }
  }


  @Override
  public int update(Badge badge) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("BadgeDao.update", badge);
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("BadgeDao.delete", no);
    }
  }


}
