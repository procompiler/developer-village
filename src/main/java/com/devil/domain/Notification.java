package com.devil.domain;

import java.util.Date;

public class Notification {
  int no;
  int userNo;
  Date createdDate;
  String content; 
  int type; //1. 게시글 2. 댓글  3. 팔로워  4. 뱃지
  String url;
  String photo;
  
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
  public String getContent() {
    return content;
  }
  public Notification setContent(String content) {
    this.content = content;
    return this;
  }
  public int getType() {
    return type;
  }
  public Notification setType(int type) {
    this.type = type;
    return this;
  }
  public String getUrl() {
    return url;
  }
  public Notification setUrl(String url) {
    this.url = url;
    return this;
  }
  public String getPhoto() {
    return photo;
  }
  public Notification setPhoto(String photo) {
    this.photo = photo;
    return this;
  }
}