package com.devil.dao;

import java.util.List;
import com.devil.domain.Bdg_stan;

public interface Bdg_stanDao {
  public int insert(Bdg_stan bdg_stan) throws Exception;
  public int update(Bdg_stan bdg_stan) throws Exception;
  public List<Bdg_stan> findAll(String keyword) throws Exception;
  public Bdg_stan findByNo(int no) throws Exception;
  public int delete(int no) throws Exception;
}
