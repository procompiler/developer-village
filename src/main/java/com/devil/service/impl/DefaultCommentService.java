package com.devil.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.devil.dao.CommentDao;
import com.devil.dao.NotificationDao;
import com.devil.domain.Article;
import com.devil.domain.Comment;
import com.devil.domain.Notification;
import com.devil.domain.User;
import com.devil.service.CommentService;

@Service
public class DefaultCommentService implements CommentService {
  CommentDao commentDao;
  NotificationDao notificationDao;

  public DefaultCommentService(CommentDao commentDao, NotificationDao notificationDao) {
    this.commentDao = commentDao;
    this.notificationDao = notificationDao;
  }

  @Override
  public int add(Comment comment) throws Exception {
    int count = commentDao.insert(comment);
    notificationDao.insert(makeNoti(comment));
    return count;
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
  // 알림 만들기
  public Notification makeNoti(Comment comment) {
    Notification notification = new Notification()
        .setComment(comment);
    if (comment.getMotherNo() == 0) {
      return notification.setType(1);
    }
    return notification.setType(2);
  }
}
