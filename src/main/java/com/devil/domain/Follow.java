package com.devil.domain;

import java.util.Date;

public class Follow {
  int userNo;
  int followeeNo;
  Date createdDate;

  public int getUserNo() {
    return userNo;
  }
  public void setUserNo(int userNo) {
    this.userNo = userNo;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  public int getFolloweeNo() {
    return followeeNo;
  }
  public void setFolloweeNo(int followeeNo) {
    this.followeeNo = followeeNo;
  }

}
