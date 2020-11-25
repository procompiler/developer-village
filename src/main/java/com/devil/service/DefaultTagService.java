package com.devil.service;

import java.util.List;

import com.devil.dao.TagDao;
import com.devil.domain.Tag;

public class DefaultTagService implements TagService {
  TagDao tagDao;
  
  public DefaultTagService(TagDao tagDao) {
    this.tagDao = tagDao;
  }
  
  @Override
  public int add(Tag tag) throws Exception {
    return tagDao.insert(tag);
  }

  @Override
  public List<Tag> list(String keyword) throws Exception {
    return tagDao.findAll(keyword);
  }
  
}
