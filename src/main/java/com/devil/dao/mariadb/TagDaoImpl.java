package com.devil.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import com.devil.dao.TagDao;
import com.devil.domain.Tag;
import com.devil.domain.User;

@Repository
public class TagDaoImpl implements TagDao {

  SqlSessionFactory sqlSessionFactory;

  public TagDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Tag tag) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.insert("TagDao.insert", tag);
    }
  }

  @Override
  public List<Tag> findAll(String keyword) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("TagDao.findAll", keyword);
    }
  }

  @Override
  public int update(Tag tag) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("TagDao.update", tag);
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("TagDao.delete", no);
    }
  }

  @Override
  public Tag findByNo(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("TagDao.findByNo", no);
    }
  }

  @Override
  public int inactive(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("TagDao.inactive", no);
    }
  }

  @Override
  public List<Tag> findByFollower(User user) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("TagDao.findByFollower", user);
    }
  }
}
