package com.devil.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.devil.dao.ReportDao;
import com.devil.domain.Report;
import com.devil.service.ReportService;

@Service
public class DefaultReportService implements ReportService {
  ReportDao reportDao;


  public DefaultReportService(ReportDao reportDao) {
    this.reportDao = reportDao;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public int reportArticle(Report report) throws Exception {
    reportDao.insert(report);
    reportDao.insertReportedArticle(report);
    return 1;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public int reportComment(Report report) throws Exception {
    reportDao.insert(report);
    reportDao.insertReportedComment(report);
    return 1;
  }

  @Override
  public List<Report> articleList(String keyword) throws Exception {
    return reportDao.findAllArticle(keyword);
  }

  @Override
  public List<Report> commentList(String keyword) throws Exception {
    return reportDao.findAllComment(keyword);
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
