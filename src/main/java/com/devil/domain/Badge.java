package com.devil.domain;

import java.util.Date;
import java.util.List;

public class Badge {
  private int no;
  private String name;
  private String photo;
  private Tag tag;
  private int state; // 활성상태 0 안보임 1 보임
  private String content;
  private Date collectDate;
  private List<BadgeStan> badgeStans;
  
  public int getState() {
    return state;
  }

  public Badge setState(int state) {
    this.state = state;
    return this;
  }

  public int getNo() {
    return no;
  }

  public Badge setNo(int no) {
    this.no = no;
    return this;
  }

  public String getName() {
    return name;
  }

  public Badge setName(String name) {
    this.name = name;
    return this;
  }

  public String getPhoto() {
    return photo;
  }

  public Badge setPhoto(String photo) {
    this.photo = photo;
    return this;
  }


  public Tag getTag() {
    return tag;
  }

  public void setTag(Tag tag) {
    this.tag = tag;
  }

  public String getContent() {
    return content;
  }

  public Badge setContent(String content) {
    this.content = content;
    return this;
  }

  public Date getCollectDate() {
    return collectDate;
  }

  public Badge setCollectDate(Date collectDate) {
    this.collectDate = collectDate;
    return this;
  }

  public List<BadgeStan> getBadgeStans() {
    return badgeStans;
  }

  public void setBadgeStans(List<BadgeStan> badgeStans) {
    this.badgeStans = badgeStans;
  }
  
  
}
