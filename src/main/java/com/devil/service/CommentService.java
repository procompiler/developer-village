package com.devil.service;

import java.util.List;
import com.devil.domain.Article;
import com.devil.domain.Comment;
import com.devil.domain.User;

public interface CommentService {
  int add(Comment comment) throws Exception;
  List<Comment> list (String keyword) throws Exception;
  //Comment get(int no) throws Exception;
  int update(Comment comment) throws Exception;
  int delete(int commentNo) throws Exception;
  int undelete(int commentNo) throws Exception;
  List<Comment> getByArticleNo (int articleNo) throws Exception;
  Article getArticleByCommentNo(int commentNo) throws Exception;
  Comment getMotherComment(int commentNo) throws Exception;
  List<Comment> listByWriter(User user) throws Exception;
  Comment get(int commentNo) throws Exception;
}
