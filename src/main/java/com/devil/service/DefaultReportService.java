package com.devil.service;

import java.util.List;
import com.devil.dao.ReportDao;
import com.devil.domain.Report;

public class DefaultReportService implements ReportService {
  ReportDao reportDao;

  public DefaultReportService(ReportDao reportDao) {
    this.reportDao = reportDao;
  }

  @Override
  public int report(Report report) throws Exception {
    return reportDao.insert(report);
  }

  @Override
  public List<Report> list(String keyword) throws Exception {
    return null;
  }

  @Override
  public Report get(int no) throws Exception {
    return null;
  }

  @Override
  public int update(Report report) throws Exception {
    return 0;
  }


}
