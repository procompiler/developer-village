package com.devil.dao;

import java.util.Map;
import com.devil.domain.Collect;

public interface CollectDao {
  int insert(Collect collect) throws Exception;
  int delete(Collect collect) throws Exception;
  int updateOrder(Collect collect) throws Exception;
  int findCountByUser(Map<String, Object> params) throws Exception;
}
