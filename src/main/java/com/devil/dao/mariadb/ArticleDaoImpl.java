package com.devil.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.devil.dao.ArticleDao;
import com.devil.domain.Article;

public class ArticleDaoImpl implements ArticleDao {
  
  SqlSessionFactory sqlSessionFactory;

  public ArticleDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  public int insert(Article article) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.insert("ArticleDao.insert", article);
    }
  }
  
  @Override
  public List<Article> findAll(String keyword) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("ArticleDao.findAll", keyword);
    }
  }
}
