package com.devil.domain;

import java.util.List;

public class Badge {
  private int no;
  private String name;
  private String photo;
  private List<Tag> tagNo;
  private int state; // 활성상태 0 안보임 1 보임
  private String content;


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

  public List<Tag> getTagNo() {
    return tagNo;
  }

  public Badge setTagNo(List<Tag> tagNo) {
    this.tagNo = tagNo;
    return this;
  }

  public String getContent() {
    return content;
  }

  public Badge setContent(String content) {
    this.content = content;
    return this;
  }

}
