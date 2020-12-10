package com.devil.dao;

import java.util.List;
import com.devil.domain.Usr_bdg;

public interface Usr_bdgDao {
  int insert(Usr_bdg usr_bdg) throws Exception;
  int delete(int no) throws Exception;
  Usr_bdg findByNo(int no) throws Exception;
  List<Usr_bdg> findAll(String keyword) throws Exception;
  int update(Usr_bdg usr_bdg) throws Exception;
}
