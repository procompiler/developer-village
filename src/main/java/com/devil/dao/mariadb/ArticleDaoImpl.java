package com.devil.dao.mariadb;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.devil.dao.ArticleDao;
import com.devil.domain.Article;

public class ArticleDaoImpl implements ArticleDao {

  SqlSessionFactory sqlSessionFactory;

  public ArticleDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Article article) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.insert("ArticleDao.insert", article);
    }
  }

  @Override
  public Article findByNo(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("ArticleDao.findByNo", no);
    }
  }

  @Override
  public List<Article> findByDetailKeyword(Map<String, Object> keywords) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("ArticleDao.findByDetailKeyword", keywords);
    }
  }

  @Override
  public List<Article> findByCategoryNo(int categoryNo) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("ArticleDao.findByCategoryNo", categoryNo);
    }
  }

  @Override
  public List<Article> findAll(String keyword) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("ArticleDao.findAll", keyword);
    }
  }

  @Override
  public int updateViewCount(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("ArticleDao.updateViewCount", no);
    }
  }

  @Override
  public int update(Article article) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("ArticleDao.update", article);
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("ArticleDao.delete", no);
    }
  }

  @Override
  public int insertTags(Article article) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("ArticleDao.insertTags", article);
    }
  }
  @Override
  public int inactive(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("ArticleDao.inactive", no);
    }
  }
}
