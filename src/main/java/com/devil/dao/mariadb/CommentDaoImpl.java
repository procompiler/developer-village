package com.devil.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.devil.dao.CommentDao;
import com.devil.domain.Comment;

public class CommentDaoImpl implements CommentDao {
  SqlSessionFactory sqlSessionFactory;

  public CommentDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Comment comment) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.insert("CommentDao.insert", comment);
    }
  }

  @Override
  public int update(Comment comment) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("CommentDao.update", comment);
    }
  }

  @Override
  public List<Comment> findAll(String keyword) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("CommentDao.findAll", keyword);
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("CommentDao.delete", no);
    }
  }

  @Override
  public Comment findByNo(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("CommentDao.findByNo", no);
    }
  }

  @Override
  public int updateViewCount(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("CommentDao.updateViewCount", no);
    }
  }

}
