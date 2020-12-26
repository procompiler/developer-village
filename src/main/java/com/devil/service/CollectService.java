package com.devil.service;

import com.devil.domain.Collect;

public interface CollectService {
  int add(Collect collect) throws Exception;
  int delete(Collect collect) throws Exception;
  int updateOrder(Collect collect) throws Exception;
}