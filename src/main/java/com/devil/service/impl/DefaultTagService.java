package com.devil.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.devil.dao.TagDao;
import com.devil.domain.Tag;
import com.devil.domain.User;
import com.devil.service.TagService;

@Service
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

  @Override
  public Tag get(int no) throws Exception {
    return tagDao.findByNo(no);
  }

  @Override
  public int update(Tag tag) throws Exception {
    return tagDao.update(tag);
  }

  @Override
  public int delete(int no) throws Exception {
    return tagDao.inactive(no);
  }

  @Override
  public List<Tag> listByFollower(User user) throws Exception {
    return tagDao.findByFollower(user);
  }

}
