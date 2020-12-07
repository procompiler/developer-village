package com.devil.service;

import java.util.List;
import com.devil.dao.ReportDao;
import com.devil.domain.Report;
import com.devil.util.SqlSessionFactoryProxy;

public class DefaultReportService implements ReportService {
  ReportDao reportDao;
  SqlSessionFactoryProxy factoryProxy;


  public DefaultReportService(ReportDao reportDao, SqlSessionFactoryProxy factoryProxy) {
    this.reportDao = reportDao;
    this.factoryProxy = factoryProxy;
  }

  @Override
  public int report(Report report) throws Exception {
    try {
      factoryProxy.startTransaction();
      reportDao.insert(report);
      reportDao.insertReportedArticle(report);
      factoryProxy.commit();
      return 1;
    } catch (Exception e) {
      factoryProxy.rollback();
      throw e;
    } finally {
      factoryProxy.endTransaction();
    }
  }

  @Override
  public List<Report> list(String keyword) throws Exception {
    return reportDao.findAll(keyword);
  }

  @Override
  public Report get(int no) throws Exception {
    Report report = reportDao.findByNo(no);
    return report;
  }
}
