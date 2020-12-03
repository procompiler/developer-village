package com.devil.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.devil.dao.ReportDao;
import com.devil.domain.Report;

public class ReportDaoImpl implements ReportDao {

  SqlSessionFactory sqlSessionFactory;

  public ReportDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Report report) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.insert("ReportDao.insert", report);
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("ReportDao.delete", no);
    }
  }

  @Override
  public List<Report> findAll(String keyword) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("ReportDao.findAll", keyword);
    }
  }

  @Override
  public int update(Report report) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("ReportDao.update", report);
    }
  }

}
