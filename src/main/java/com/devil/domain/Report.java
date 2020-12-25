package com.devil.domain;

import java.sql.Date;

public class Report {
  private int no;
  private int reportTypeNo;
  private Date CreatedDate;
  private Date processDate;
  private int status; // 처리상태 1 차단전 2차단중 3 차단해제
  private User reporter;
  private User reportedUser;
  private Article reportedArticle;
  private Comment reportedComment;

  public Article getReportedArticle() {
    return reportedArticle;
  }
  public Report setReportedArticle(Article reportedArticle) {
    this.reportedArticle = reportedArticle;
    return this;
  }
  public Comment getReportedComment() {
    return reportedComment;
  }
  public Report setReportedComment(Comment reportedComment) {
    this.reportedComment = reportedComment;
    return this;
  }
  public User getReportedUser() {
    return reportedUser;
  }
  public Report setReportedUser(User reportedUser) {
    this.reportedUser = reportedUser;
    return this;
  }
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
  public User getReporter() {
    return reporter;
  }
  public Report setReporter(User reporter) {
    this.reporter = reporter;
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

}
