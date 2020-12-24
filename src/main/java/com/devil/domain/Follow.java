package com.devil.domain;

import java.util.Date;

public class Follow {
  User follower;
  int followeeNo;
  Date createdDate;

  public User getFollower() {
    return follower;
  }
  public Follow setFollower(User follower) {
    this.follower = follower;
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
