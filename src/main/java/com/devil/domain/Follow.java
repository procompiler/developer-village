package com.devil.domain;

import java.util.Date;

public class Follow {
  int userNo;
  int followeeNo;
  Date createdDate;

  public int getUserNo() {
    return userNo;
  }
  public Follow setUserNo(int userNo) {
    this.userNo = userNo;
    return this;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public Follow setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
    return this;
  }
  public int getFolloweeNo() {
    return followeeNo;
  }
  public Follow setFolloweeNo(int followeeNo) {
    this.followeeNo = followeeNo;
    return this;
  }

}
