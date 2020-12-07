package com.devil.service;

import java.util.List;
import com.devil.domain.Report;

public interface ReportService {
  int report(Report report) throws Exception;
  List<Report> list (String keyword) throws Exception;
  Report get(int no) throws Exception;
}
