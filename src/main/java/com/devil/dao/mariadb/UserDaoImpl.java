package com.devil.dao.mariadb;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.devil.dao.UserDao;
import com.devil.domain.User;

public class UserDaoImpl implements UserDao {

  SqlSessionFactory sqlSessionFactory;

  public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(User user) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.insert("UserDao.insert", user);
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("UserDao.delete", no);
    }
  }

  @Override
  public User findByNo(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("UserDao.findByNo", no);
    }
  }

  @Override
  public List<User> findByName(String name) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      List<User> users = sqlSession.selectList("UserDao.findByName", name);
      return users;
    }
  }

  @Override
  public List<User> findAll(String keyword) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("UserDao.findAll", keyword);
    }
  }

  @Override
  public int update(User user) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("UserDao.update", user);
    }
  }

  @Override
  public User findByEmailPassword(String email, String password) throws Exception {
    HashMap<String,Object> map = new HashMap<>();
    map.put("email", email);
    map.put("password", password);

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("UserDao.findByEmailPassword", map);
    }
  }
}
