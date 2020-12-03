package com.devil.domain;

import java.sql.Date;

public class Report {
  private int no;
  private int reportTypeNo;
  private User reporter;
  private Date CreatedDate;
  private int status;
  private Date processDate;

  public int getNo() {
    return no;
  }
  public Report setNo(int no) {
    this.no = no;
    return this;
  }
  public int getReportTypeNo() {
    return reportTypeNo;
  }
  public Report setReportTypeNo(int reportTypeNo) {
    this.reportTypeNo = reportTypeNo;
    return this;
  }
  public Date getCreatedDate() {
    return CreatedDate;
  }
  public Report setCreatedDate(Date createdDate) {
    CreatedDate = createdDate;
    return this;
  }
  public int getStatus() {
    return status;
  }
  public Report setStatus(int status) {
    this.status = status;
    return this;
  }
  public Date getProcessDate() {
    return processDate;
  }
  public Report setProcessDate(Date processDate) {
    this.processDate = processDate;
    return this;
  }
  public User getReporter() {
    return reporter;
  }
  public Report setReporter(User reporter) {
    this.reporter = reporter;
    return this;
  }

}
