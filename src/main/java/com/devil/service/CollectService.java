package com.devil.service;

import java.util.List;
import java.util.Map;
import com.devil.domain.Collect;

public interface CollectService {
  int add(Collect collect) throws Exception;
  int delete(Collect collect) throws Exception;
  int updateAllOrder(List<Collect> collects) throws Exception;
  int getCount(Map<String, Object> params) throws Exception;
}