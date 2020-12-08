package com.devil.domain;

import java.util.Date;

public class Follow {
  int userNo;
  int followNo;
  Date createdDate;

  public int getUserNo() {
    return userNo;
  }
  public void setUserNo(int userNo) {
    this.userNo = userNo;
  }
  public int getFollowNo() {
    return followNo;
  }
  public void setFollowNo(int followNo) {
    this.followNo = followNo;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
}
