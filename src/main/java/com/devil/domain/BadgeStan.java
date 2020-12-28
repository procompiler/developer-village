package com.devil.domain;

public class BadgeStan {
  private int no;
  private int badgeNo;
  private int evaluationNo;
  private String evaluationName;
  private int count;
  
  public int getNo() {
    return no;
  }
  public BadgeStan setNo(int no) {
    this.no = no;
    return this;
  }
  public int getEvaluationNo() {
    return evaluationNo;
  }
  public BadgeStan setEvaluationNo(int evaluationNo) {
    this.evaluationNo = evaluationNo;
    return this;
  }
  public String getEvaluationName() {
    return evaluationName;
  }
  public BadgeStan setEvaluationName(String evaluationName) {
    this.evaluationName = evaluationName;
    return this;
  }
  public int getCount() {
    return count;
  }
  public BadgeStan setCount(int count) {
    this.count = count;
    return this;
  }
  public int getBadgeNo() {
    return badgeNo;
  }
  public BadgeStan setBadgeNo(int badgeNo) {
    this.badgeNo = badgeNo;
    return this;
  }
}
