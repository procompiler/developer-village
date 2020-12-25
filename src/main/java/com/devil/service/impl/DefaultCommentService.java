package com.devil.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.devil.dao.CommentDao;
import com.devil.domain.Article;
import com.devil.domain.Comment;
import com.devil.domain.User;
import com.devil.service.CommentService;

@Service
public class DefaultCommentService implements CommentService {
  CommentDao commentDao;

  public DefaultCommentService(CommentDao commentDao) {
    this.commentDao = commentDao;
  }

  @Override
  public int add(Comment comment) throws Exception {
    return commentDao.insert(comment);
  }

  @Override
  public List<Comment> list(String keyword) throws Exception {
    return commentDao.findAll(keyword);
  }

  @Override
  public List<Comment> getByArticleNo(int articleNo) throws Exception {
    return commentDao.findByArticleNo(articleNo);
  }

  @Override
  public Article getArticleByCommentNo(int commentNo) throws Exception {
    return commentDao.findArticleByCommentNo(commentNo);
  }

  @Override
  public Comment getMotherComment(int commentNo) throws Exception {
    return commentDao.findMotherComment(commentNo);
  }

  @Override
  public Comment get(int commentNo) throws Exception {
    return commentDao.findByNo(commentNo);
  }

  @Override
  public int update(Comment comment) throws Exception {
    return commentDao.update(comment);
  }

  @Override
  public int delete(int commentNo) throws Exception {
    return commentDao.inactivate(commentNo);
  }

  @Override
  public int undelete(int commentNo) throws Exception {
    return commentDao.activate(commentNo);
  }

  @Override
  public List<Comment> listByWriter(User user) throws Exception {
    return commentDao.findByWriter(user);
  }
}
