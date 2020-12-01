package com.devil.domain;

public class Badge {
  private int no;
  private String name;
  private String photo;
  private int tagNo;
  private String content;

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

  public int getTagNo() {
    return tagNo;
  }

  public Badge setTagNo(int tagNo) {
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
