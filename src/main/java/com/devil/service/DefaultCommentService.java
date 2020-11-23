package com.devil.service;

import com.devil.dao.CommentDao;
import com.devil.domain.Comment;

public class DefaultCommentService implements CommentService {
  CommentDao commentDao;
  
  public DefaultCommentService(CommentDao commentDao) {
    this.commentDao = commentDao;
  }
  
  @Override
  public int add(Comment comment) throws Exception {
    return commentDao.insert(comment);
  }
}
