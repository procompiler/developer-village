package com.devil.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import com.devil.dao.CommentDao;
import com.devil.domain.Comment;
import com.devil.domain.User;

@Repository
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
  public int inactive(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("CommentDao.inactive", no);
    }
  }

  @Override
  public List<Comment> findByArticleNo(int articleNo) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("CommentDao.findByArticleNo", articleNo);
    }
  }

  @Override
  public List<Comment> findByWriter(User user) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("CommentDao.findByWriter", user);
    }
  }

}
