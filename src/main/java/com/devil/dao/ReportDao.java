package com.devil.dao;

import java.util.List;
import com.devil.domain.Report;

public interface ReportDao {
  public int insert(Report report) throws Exception;
  public int update(Report report) throws Exception;
  public List<Report> findAll(String keyword) throws Exception;
  public int delete(int no) throws Exception;
}
