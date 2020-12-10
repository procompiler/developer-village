package com.devil.dao;

import java.util.List;
import com.devil.domain.Bdg_eva;

public interface Bdg_evaDao {
  public int insert(Bdg_eva bdg_eva) throws Exception; //추가
  public int update(Bdg_eva bdg_eva) throws Exception; //변경
  public List<Bdg_eva> findAll(String keyword) throws Exception; //조회
  public Bdg_eva findByNo(int no) throws Exception; //자세히보기
  public int delete(int no) throws Exception;// 지우기
}
