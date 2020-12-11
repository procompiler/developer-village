package com.devil.dao.mariadb;

import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import com.devil.dao.BookmarkDao;
import com.devil.domain.Bookmark;

@Repository
public class BookmarkDaoImpl implements BookmarkDao {

  SqlSessionFactory sqlSessionFactory;

  public BookmarkDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Bookmark bookmark) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.insert("BookmarkDao.insert", bookmark);
    }
  }

  @Override
  public int delete(Bookmark bookmark) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("BookmarkDao.delete", bookmark);
    }
  }

  @Override
  public Bookmark findByUserArticle(Map<String, Object> map) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("BookmarkDao.findByUserArticle", map);
    }
  }
}
