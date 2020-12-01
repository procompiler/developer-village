package com.devil.domain;

public class Tag {
  private int no;
  private String name;
  private String photo;
  private String tagColor;
  private String fontColor;
  
  public int getNo() {
    return no;
  }
  public Tag setNo(int no) {
    this.no = no;
    return this;
  }
  public String getName() {
    return name;
  }
  public Tag setName(String name) {
    this.name = name;
    return this;
  }
  public String getPhoto() {
    return photo;
  }
  public Tag setPhoto(String photo) {
    this.photo = photo;
    return this;
  }
  public String getTagColor() {
    return tagColor;
  }
  public Tag setTagColor(String tagColor) {
    this.tagColor = tagColor;
    return this;
  }
  public String getFontColor() {
    return fontColor;
  }
  public Tag setFontColor(String fontColor) {
    this.fontColor = fontColor;
    return this;
  }
  

}
