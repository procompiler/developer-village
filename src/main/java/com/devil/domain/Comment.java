package com.devil.domain;

import java.util.Date;

public class Comment {
  private int no;
  private User writer;
  private String content;
  private Date createdDate;
  private int ord;
  private int step; // 댓글 단계 0 댓글 1 대댓글
  //private int parentNo; // 상위 댓글 번호
  private String updatedDate;
  private String deletedDate;
  private int state;
  private boolean selected; // 대표 답변 여부 0 미해결 1 해결
  private int articleNo;

  public int getArticleNo() {
    return articleNo;
  }
  public Comment setArticleNo(int articleNo) {
    this.articleNo = articleNo;
    return this;
  }
  public String getUpdatedDate() {
    return updatedDate;
  }
  public Comment setUpdatedDate(String updatedDate) {
    this.updatedDate = updatedDate;
    return this;

  }
  public String getDeletedDate() {
    return deletedDate;
  }
  public Comment setDeletedDate(String deletedDate) {
    this.deletedDate = deletedDate;
    return this;
  }
  public int getState() {
    return state;
  }

  public Comment setState(int state) {
    this.state = state;
    return this;
  }
  public boolean isSelected() {
    return selected;
  }
  public Comment setSelected(boolean selected) {
    this.selected = selected;
    return this;
  }
  public int getNo() {
    return no;
  }
  public Comment setNo(int no) {
    this.no = no;
    return this;
  }
  public User getWriter() {
    return writer;
  }
  public Comment setWriter(User writer) {
    this.writer = writer;
    return this;
  }
  public String getContent() {
    return content;
  }
  public Comment setContent(String content) {
    this.content = content;
    return this;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public Comment setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
    return this;
  }
  public int getOrd() {
    return ord;
  }
  public Comment setOrd(int ord) {
    this.ord = ord;
    return this;
  }
  public int getStep() {
    return step;
  }
  public Comment setStep(int step) {
    this.step = step;
    return this;
  }


}
