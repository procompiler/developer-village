package com.devil.dao;

import java.util.List;

import com.devil.domain.Tag;

public interface TagDao {
  public int insert(Tag tag) throws Exception;
  public int update(Tag tag) throws Exception;
  public List<Tag> findAll(String keyword) throws Exception;
  public int delete(int no) throws Exception;
}
