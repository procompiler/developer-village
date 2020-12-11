package com.devil.dao.mariadb;

import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import com.devil.dao.FollowDao;
import com.devil.domain.Follow;

@Repository
public class FollowDaoImpl implements FollowDao {
  SqlSessionFactory sqlSessionFactory;

  public FollowDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insertTag(Follow follow) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.insert("FollowDao.insertTag", follow);
    }
  }

  @Override
  public int deleteTag(Follow follow) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("FollowDao.deleteTag", follow);
    }
  }

  @Override
  public Follow findByUserTag(Map<String, Object> map) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("FollowDao.findByUserTag", map);
    }
  }

  @Override
  public int insertUser(Follow follow) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.insert("FollowDao.insertUser", follow);
    }
  }


  @Override
  public int deleteUser(Follow follow) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("FollowDao.deleteUser", follow);
    }
  }


  @Override
  public Follow findByUserUser(Map<String, Object> map) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("FollowDao.findByUserUser", map);
    }
  }
}
