package com.devil.domain;

import java.util.Date;

public class Bookmark {
  int userNo;
  int articleNo;
  Date createdDate;

  public int getUserNo() {
    return userNo;
  }
  public void setUserNo(int userNo) {
    this.userNo = userNo;
  }
  public int getArticleNo() {
    return articleNo;
  }
  public void setArticleNo(int articleNo) {
    this.articleNo = articleNo;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
}
