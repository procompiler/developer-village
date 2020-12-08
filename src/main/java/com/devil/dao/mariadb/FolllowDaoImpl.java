package com.devil.dao.mariadb;

import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.devil.dao.FollowDao;
import com.devil.domain.Follow;

public class FolllowDaoImpl implements FollowDao {

  SqlSessionFactory sqlSessionFactory;

  public FolllowDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Follow follow) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.insert("FollowDao.insert", follow);
    }
  }

  @Override
  public int delete(Follow follow) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("FollowDao.delete", follow);
    }
  }

  @Override
  public Follow findByUserFollow(Map<String, Object> map) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("FollowDao.findByUserArticle", map);
    }
  }
}
