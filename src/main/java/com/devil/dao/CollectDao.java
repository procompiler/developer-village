package com.devil.dao;

import java.util.List;
import java.util.Map;
import com.devil.domain.Collect;
import com.devil.domain.User;

public interface CollectDao {
  int insert(Collect collect) throws Exception;
  int delete(Collect collect) throws Exception;
  int findCountByUser(Map<String, Object> params) throws Exception;
  int insertAll(List<Collect> collects) throws Exception;
  int deleteByUser(User user) throws Exception;
  void updateOrder(Collect collect) throws Exception;
}
