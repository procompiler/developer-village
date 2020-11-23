package com.devil.service;

import com.devil.domain.Comment;

public interface CommentService {
  int add(Comment comment) throws Exception;
}
