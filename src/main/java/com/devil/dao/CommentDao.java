package com.devil.dao;

import java.util.List;
import com.devil.domain.Comment;
import com.devil.domain.User;

public interface CommentDao {
  public int insert(Comment comment) throws Exception;
  public int update(Comment comment) throws Exception;
  public List<Comment> findAll(String keyword) throws Exception;
  public int delete(int no) throws Exception;
  //public Comment findByNo(int no) throws Exception;
  public List<Comment> findByArticleNo(int articleNo) throws Exception;
  public int inactive(int no) throws Exception;
  public List<Comment> findByWriter(User user) throws Exception;
}
