package com.devil.domain;

import java.util.Date;

public class Notification {
  int no;
  int userNo;
  Date createdDate;
  int type; //1. 게시글 2. 댓글  3. 팔로워  4. 뱃지
  User follower;
  Comment comment;
  Badge badge;
  String difTime;
  
  public int getNo() {
    return no;
  }
  public Notification setNo(int no) {
    this.no = no;
    return this;
  }
  public int getUserNo() {
    return userNo;
  }
  public Notification setUserNo(int userNo) {
    this.userNo = userNo;
    return this;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public Notification setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
    return this;
  }
  public int getType() {
    return type;
  }
  public Notification setType(int type) {
    this.type = type;
    return this;
  }
  public String getDifTime() {
    return difTime;
  }
  public Notification setDifTime(String difTime) {
    this.difTime = difTime;
    return this;
  }
  public Comment getComment() {
    return comment;
  }
  public Notification setComment(Comment comment) {
    this.comment = comment;
    return this;
  }
  public User getFollower() {
    return follower;
  }
  public Notification setFollower(User follower) {
    this.follower = follower;
    return this;
  }
  public Badge getBadge() {
    return badge;
  }
  public Notification setBadge(Badge badge) {
    this.badge = badge;
    return this;
  }
}