package com.devil.dao.mariadb;

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
}
