package com.devil.service;

import java.util.List;
import com.devil.domain.Comment;

public interface CommentService {
  int add(Comment comment) throws Exception;
  List<Comment> list (String keyword) throws Exception;
  Comment get(int no) throws Exception;
  int update(Comment comment) throws Exception;
  int delete(int no) throws Exception;
}
