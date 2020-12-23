package com.devil.service;

import java.util.List;
import com.devil.domain.Comment;
import com.devil.domain.User;

public interface CommentService {
  int add(Comment comment) throws Exception;
  List<Comment> list (String keyword) throws Exception;
  //Comment get(int no) throws Exception;
  int update(Comment comment) throws Exception;
  int delete(int no) throws Exception;
  int undelete(int no) throws Exception;
  List<Comment> getByArticleNo (int articleNo) throws Exception;
  List<Comment> listByWriter(User user) throws Exception;
  Comment get(int no) throws Exception;
}
